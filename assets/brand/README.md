# The Tawam mark

Tawam (توأم) means "twin". The mark is two identical rounded squares, the
phone and the PC, one over the other; where they overlap is what they share.

| File | What it is |
|---|---|
| `icon.svg` | The full-color app icon: deep teal tile, sand and white twins |
| `icon-flat.svg` | The same without the glow |
| `icon-mono.svg` | One-color version (notifications, themed icons) |
| `icon-512.png` | `icon.svg` at 512 × 512 |

The desktop app draws the same geometry in `desktop/app/src/mark.rs`; Android
uses `android/app/src/main/res/drawable/ic_launcher_*.xml` and `ic_notification.xml`.

Colors: tile `#0E3B43`, glow `#15545D`, back twin `#F2D7A6`, front twin
`#FFFFFF`, overlap `#2E8C86`, rim `#7FD1C7`.

The Tawam name and mark are separate from the NectarLink name and mark, which
belong to the NectarLink project and are not used here.
