// SPDX-License-Identifier: GPL-3.0-or-later
//! The Tawam mark (`assets/brand/icon.svg`): two identical rounded squares,
//! the phone and the PC, one over the other on a deep teal tile; where they
//! overlap is what they share. Drawn in code at any pixel size: the tray,
//! window and toast icons at runtime, and the executable's icon at build time
//! (`build.rs` includes this file), so every size is sharp without bitmaps.

const TILE: [f32; 3] = rgb(0x0E, 0x3B, 0x43);
const GLOW: [f32; 3] = rgb(0x15, 0x54, 0x5D);
const RIM: [f32; 3] = rgb(0x7F, 0xD1, 0xC7);
const BACK: [f32; 3] = rgb(0xF2, 0xD7, 0xA6);
const FRONT: [f32; 3] = rgb(0xFF, 0xFF, 0xFF);
const SHARED: [f32; 3] = rgb(0x2E, 0x8C, 0x86);

/// Supersampling grid per pixel (SS×SS samples) for smooth edges.
const SS: usize = 4;

const fn rgb(r: u8, g: u8, b: u8) -> [f32; 3] {
    [r as f32, g as f32, b as f32]
}

/// Sizes in the 100×100 design grid: small sizes fill more of the square.
struct Geometry {
    inset: f32,
    corner: f32,
    /// The back twin's top-left corner, the front twin's offset, the twins' size and corner radius.
    back: f32,
    offset: f32,
    twin: f32,
    radius: f32,
}

impl Geometry {
    fn for_size(size: usize) -> Self {
        if size <= 32 {
            Geometry { inset: 2.0, corner: 20.0, back: 18.0, offset: 18.0, twin: 46.0, radius: 9.0 }
        } else {
            Geometry { inset: 4.0, corner: 22.0, back: 24.0, offset: 14.0, twin: 38.0, radius: 9.0 }
        }
    }
}

/// Renders the mark as straight-alpha RGBA, row by row, `size`×`size`.
pub fn render(size: usize) -> Vec<u8> {
    let g = Geometry::for_size(size);
    let unit = 100.0 / size as f32;
    let rim = (1.5f32).max(unit * 0.6);
    let in_twin = |x: f32, y: f32, at: f32| {
        let (lo, hi) = (at, at + g.twin);
        rounded_rect_distance(x, y, lo, hi, g.radius) <= 0.0
    };

    let mut out = vec![0u8; size * size * 4];
    for py in 0..size {
        for px in 0..size {
            let mut color = [0f32; 3];
            let mut covered = 0usize;
            for sy in 0..SS {
                for sx in 0..SS {
                    let x = (px as f32 + (sx as f32 + 0.5) / SS as f32) * unit;
                    let y = (py as f32 + (sy as f32 + 0.5) / SS as f32) * unit;
                    let edge = rounded_rect_distance(x, y, g.inset, 100.0 - g.inset, g.corner);
                    if edge > 0.0 {
                        continue;
                    }
                    covered += 1;
                    let back = in_twin(x, y, g.back);
                    let front = in_twin(x, y, g.back + g.offset);
                    let paint = match (back, front) {
                        (true, true) => SHARED,
                        (false, true) => FRONT,
                        (true, false) => BACK,
                        _ if -edge <= rim => mix(TILE, RIM, 0.34),
                        _ => {
                            let (dx, dy) = (x - 50.0, y - 50.0);
                            let t = ((dx * dx + dy * dy).sqrt() / 46.0).min(1.0);
                            mix(GLOW, TILE, t)
                        }
                    };
                    for c in 0..3 {
                        color[c] += paint[c];
                    }
                }
            }
            if covered == 0 {
                continue;
            }
            let i = (py * size + px) * 4;
            for c in 0..3 {
                out[i + c] = (color[c] / covered as f32).round() as u8;
            }
            out[i + 3] = ((covered as f32 / (SS * SS) as f32) * 255.0).round() as u8;
        }
    }
    out
}

fn mix(a: [f32; 3], b: [f32; 3], t: f32) -> [f32; 3] {
    [a[0] + (b[0] - a[0]) * t, a[1] + (b[1] - a[1]) * t, a[2] + (b[2] - a[2]) * t]
}

/// How far (x, y) is outside the rounded square from `lo` to `hi` (both
/// axes) with corner radius `r` (negative inside).
fn rounded_rect_distance(x: f32, y: f32, lo: f32, hi: f32, r: f32) -> f32 {
    let (a, b) = (lo + r, hi - r);
    if x > a && x < b || y > a && y < b {
        let to_x = (x - lo).min(hi - x);
        let to_y = (y - lo).min(hi - y);
        return -to_x.min(to_y);
    }
    let qx = (x.clamp(a, b) - x).abs();
    let qy = (y.clamp(a, b) - y).abs();
    (qx * qx + qy * qy).sqrt() - r
}

#[cfg(test)]
mod tests {
    use super::*;

    fn pixel(img: &[u8], size: usize, x: usize, y: usize) -> [u8; 4] {
        let i = (y * size + x) * 4;
        [img[i], img[i + 1], img[i + 2], img[i + 3]]
    }

    #[test]
    fn corners_are_transparent_and_the_overlap_sits_in_the_middle() {
        let size = 100;
        let img = render(size);
        assert_eq!(img.len(), size * size * 4);
        assert_eq!(pixel(&img, size, 0, 0)[3], 0, "rounded corner");
        assert_eq!(pixel(&img, size, 50, 50), [0x2E, 0x8C, 0x86, 255], "the shared overlap");
        assert_eq!(pixel(&img, size, 30, 30), [0xF2, 0xD7, 0xA6, 255], "the back twin");
        assert_eq!(pixel(&img, size, 70, 70), [0xFF, 0xFF, 0xFF, 255], "the front twin");
        let tile = pixel(&img, size, 50, 10);
        assert!(tile[0] < 0x40 && tile[3] == 255, "{tile:?}");
    }

    #[test]
    fn renders_every_tray_size() {
        for size in [16, 20, 24, 32, 48, 64, 256] {
            let img = render(size);
            assert!(img.chunks(4).any(|p| p[3] == 255), "{size}px has an opaque tile");
            assert!(img.chunks(4).any(|p| p[0] == 0xFF && p[1] == 0xFF && p[3] == 255), "{size}px shows the front twin");
        }
    }
}
