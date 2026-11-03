// SPDX-License-Identifier: GPL-3.0-or-later
// Generated from docs/design/tokens.json by `cargo xtask tokens`. Do not edit.
pragma Singleton
import QtQuick

QtObject {
    readonly property var data: ({
      "elevation": {
        "bloom": {
          "level0": "none",
          "level1": "0 1px 2px rgba(0,0,0,.06)",
          "window": "0 1px 2px rgba(0,0,0,.06), 0 24px 60px -20px rgba(0,0,0,.18)"
        },
        "graphite": {
          "level0": "none",
          "level1": "none",
          "window": "0 30px 70px -30px rgba(0,0,0,.35)"
        }
      },
      "icons": {
        "set": "Material Symbols Rounded (Apache-2.0)",
        "size": 20,
        "stroke": "outlined, 400 weight; filled when selected"
      },
      "layout": {
        "desktop": {
          "contentPadding": 22,
          "gutter": 16,
          "minWindow": [
            880,
            600
          ],
          "railWidth": 80,
          "topBarHeight": 52
        },
        "phone": {
          "gutter": 10,
          "screenPadding": 16,
          "tabBarHeight": 64
        }
      },
      "motion": {
        "description": "Springs everywhere; durations are only for fades. Respect reduced-motion: replace movement with 120 ms fades.",
        "fade": {
          "fast": 120,
          "normal": 200
        },
        "hoverLift": 2,
        "pressScale": 0.97,
        "spring": {
          "gentle": {
            "damping": 26,
            "stiffness": 220,
            "use": "page and panel transitions"
          },
          "snappy": {
            "damping": 34,
            "stiffness": 620,
            "use": "toggles, chips, buttons, hover lift"
          },
          "standard": {
            "damping": 30,
            "stiffness": 380,
            "use": "cards, sheets, expanding a notification into a conversation"
          }
        }
      },
      "spacing": {
        "scale": [
          0,
          2,
          4,
          6,
          8,
          10,
          12,
          16,
          20,
          24,
          32,
          40,
          48,
          64
        ],
        "unit": 4
      },
      "themes": {
        "bloom": {
          "defaultSeed": "teal",
          "description": "Tawam: calm, flat surfaces with a deep-teal accent by default; color only where it carries meaning.",
          "dynamicColor": true,
          "fonts": {
            "display": "IBM Plex Sans",
            "label": "IBM Plex Sans",
            "mono": "IBM Plex Mono",
            "ui": "IBM Plex Sans"
          },
          "label": {
            "case": "none",
            "size": 12.5,
            "tracking": 0.01,
            "weight": 600
          },
          "seeds": {
            "indigo": {
              "dark": {
                "onPrimary": "#1F275C",
                "onPrimaryContainer": "#DFE1F4",
                "onSecondaryContainer": "#E0E2EB",
                "onSurface": "#E5E5E8",
                "onSurfaceVariant": "#BFC1C9",
                "outline": "#8D909E",
                "outlineVariant": "#454650",
                "primary": "#BFC4EA",
                "primaryContainer": "#313E94",
                "secondaryContainer": "#3C415E",
                "surface": "#151619",
                "surfaceContainer": "#1D1D22",
                "surfaceContainerHigh": "#27282D",
                "surfaceContainerHighest": "#31333A",
                "surfaceContainerLow": "#101113"
              },
              "light": {
                "onPrimary": "#FFFFFF",
                "onPrimaryContainer": "#13193A",
                "onSecondaryContainer": "#191B27",
                "onSurface": "#1B1C20",
                "onSurfaceVariant": "#454650",
                "outline": "#747688",
                "outlineVariant": "#C5C6CE",
                "primary": "#3E4FBA",
                "primaryContainer": "#DFE1F4",
                "secondaryContainer": "#E0E2EB",
                "surface": "#F9F9FA",
                "surfaceContainer": "#EDEDF0",
                "surfaceContainerHigh": "#E7E8EB",
                "surfaceContainerHighest": "#DFDFE4",
                "surfaceContainerLow": "#F3F3F5"
              },
              "seed": "#5B6AC8"
            },
            "ink": {
              "dark": {
                "onPrimary": "#282C31",
                "onPrimaryContainer": "#E0E3E5",
                "onSecondaryContainer": "#E0E3E5",
                "onSurface": "#E4E6E7",
                "onSurfaceVariant": "#BFC2C4",
                "outline": "#8D9296",
                "outlineVariant": "#45474A",
                "primary": "#C1C7CD",
                "primaryContainer": "#40474F",
                "secondaryContainer": "#3C434A",
                "surface": "#151516",
                "surfaceContainer": "#1C1D1F",
                "surfaceContainerHigh": "#26282A",
                "surfaceContainerHighest": "#303235",
                "surfaceContainerLow": "#101111"
              },
              "light": {
                "onPrimary": "#FFFFFF",
                "onPrimaryContainer": "#191B1E",
                "onSecondaryContainer": "#191B1E",
                "onSurface": "#1A1B1D",
                "onSurfaceVariant": "#45474A",
                "outline": "#73777C",
                "outlineVariant": "#C4C7C9",
                "primary": "#515A64",
                "primaryContainer": "#E0E3E5",
                "secondaryContainer": "#E0E3E5",
                "surface": "#F9FAFA",
                "surfaceContainer": "#EDEEEE",
                "surfaceContainerHigh": "#E7E8E9",
                "surfaceContainerHighest": "#DEE0E1",
                "surfaceContainerLow": "#F3F4F4"
              },
              "seed": "#2B3036"
            },
            "olive": {
              "dark": {
                "onPrimary": "#272F13",
                "onPrimaryContainer": "#DCE7C3",
                "onSecondaryContainer": "#E0E5D6",
                "onSurface": "#E4E5E2",
                "onSurfaceVariant": "#C0C2B9",
                "outline": "#8E9382",
                "outlineVariant": "#45483E",
                "primary": "#B9CF88",
                "primaryContainer": "#3E4C1F",
                "secondaryContainer": "#3E452C",
                "surface": "#151513",
                "surfaceContainer": "#1C1E1A",
                "surfaceContainerHigh": "#272923",
                "surfaceContainerHighest": "#31342D",
                "surfaceContainerLow": "#11110F"
              },
              "light": {
                "onPrimary": "#FFFFFF",
                "onPrimaryContainer": "#181E0C",
                "onSecondaryContainer": "#191D12",
                "onSurface": "#1A1C18",
                "onSurfaceVariant": "#45483E",
                "outline": "#747969",
                "outlineVariant": "#C5C7BF",
                "primary": "#4F6027",
                "primaryContainer": "#DCE7C3",
                "secondaryContainer": "#E0E5D6",
                "surface": "#F9FAF9",
                "surfaceContainer": "#EDEEEC",
                "surfaceContainerHigh": "#E7E8E5",
                "surfaceContainerHighest": "#DFE0DC",
                "surfaceContainerLow": "#F3F4F2"
              },
              "seed": "#647932"
            },
            "sand": {
              "dark": {
                "onPrimary": "#3C2811",
                "onPrimaryContainer": "#F0E0CD",
                "onSecondaryContainer": "#E7E2DA",
                "onSurface": "#E6E5E2",
                "onSurfaceVariant": "#C5C0BB",
                "outline": "#988F86",
                "outlineVariant": "#4B463F",
                "primary": "#E2C19A",
                "primaryContainer": "#5F411C",
                "secondaryContainer": "#4D4031",
                "surface": "#171614",
                "surfaceContainer": "#1F1D1B",
                "surfaceContainerHigh": "#2A2724",
                "surfaceContainerHighest": "#35322E",
                "surfaceContainerLow": "#12100F"
              },
              "light": {
                "onPrimary": "#FFFFFF",
                "onPrimaryContainer": "#25190B",
                "onSecondaryContainer": "#201B14",
                "onSurface": "#1D1C19",
                "onSurfaceVariant": "#4B463F",
                "outline": "#7E756B",
                "outlineVariant": "#CAC5C0",
                "primary": "#785123",
                "primaryContainer": "#F0E0CD",
                "secondaryContainer": "#E7E2DA",
                "surface": "#FAF9F9",
                "surfaceContainer": "#EFEDEC",
                "surfaceContainerHigh": "#E9E8E5",
                "surfaceContainerHighest": "#E2DFDC",
                "surfaceContainerLow": "#F4F4F2"
              },
              "seed": "#99682C"
            },
            "teal": {
              "dark": {
                "onPrimary": "#0C3233",
                "onPrimaryContainer": "#B8ECEE",
                "onSecondaryContainer": "#D7E5E6",
                "onSurface": "#E1E6E7",
                "onSurfaceVariant": "#B7C3C4",
                "outline": "#7E9596",
                "outlineVariant": "#3D494A",
                "primary": "#6BD6DC",
                "primaryContainer": "#134E52",
                "secondaryContainer": "#2E4648",
                "surface": "#131617",
                "surfaceContainer": "#191E1F",
                "surfaceContainerHigh": "#22292A",
                "surfaceContainerHighest": "#2B3435",
                "surfaceContainerLow": "#0E1212"
              },
              "light": {
                "onPrimary": "#FFFFFF",
                "onPrimaryContainer": "#071E20",
                "onSecondaryContainer": "#131E1E",
                "onSurface": "#181D1D",
                "onSurfaceVariant": "#3D494A",
                "outline": "#657B7C",
                "outlineVariant": "#BDC9C9",
                "primary": "#186366",
                "primaryContainer": "#B8ECEE",
                "secondaryContainer": "#D7E5E6",
                "surface": "#F8F9FA",
                "surfaceContainer": "#EBEFEF",
                "surfaceContainerHigh": "#E4E9E9",
                "surfaceContainerHighest": "#DAE1E1",
                "surfaceContainerLow": "#F1F4F4"
              },
              "seed": "#1F7E82"
            }
          },
          "shape": {
            "full": 999,
            "lg": 14,
            "md": 10,
            "sm": 6,
            "xl": 18,
            "xs": 4
          },
          "status": {
            "dark": {
              "error": "#FFB4AB",
              "onError": "#690005",
              "success": "{primary}",
              "warning": "#F2C14E"
            },
            "light": {
              "error": "#BA1A1A",
              "onError": "#FFFFFF",
              "success": "{primary}",
              "warning": "#7D5200"
            }
          }
        },
        "graphite": {
          "description": "Retired in Tawam (not offered).",
          "dynamicColor": false,
          "fonts": {
            "display": "IBM Plex Sans",
            "label": "IBM Plex Sans",
            "mono": "IBM Plex Mono",
            "note": "IBM Plex Sans",
            "ui": "IBM Plex Sans"
          },
          "label": {
            "case": "upper",
            "size": 10.5,
            "tracking": 0.12,
            "weight": 400
          },
          "rules": [
            "Cards use 1px outlineVariant borders, no fills, no elevation.",
            "Primary buttons are ink-filled with the flat offset buttonShadow.",
            "Crop marks frame hero imagery; 'FIG. NN // ...' annotations label sections.",
            "At most one handwritten (Caveat) margin note per screen, only for delight moments.",
            "Never use Graphite's art effects in the app: no smudge physics, no gritty lettering, no scroll sound.",
            "Success and completed states use ink (Paper) or chalk (Slate), never green."
          ],
          "shape": {
            "full": 3,
            "lg": 4,
            "md": 4,
            "sm": 3,
            "xl": 6,
            "xs": 2
          },
          "variants": {
            "paper": {
              "buttonShadow": "3px 3px 0 #D5CFC2",
              "error": "#9E2B25",
              "grain": 0.22,
              "grid": "rgba(74,71,67,0.06)",
              "onError": "#F5F2EB",
              "onPrimary": "#F5F2EB",
              "onPrimaryContainer": "#1A1918",
              "onSecondaryContainer": "#1A1918",
              "onSurface": "#1A1918",
              "onSurfaceVariant": "#5E5A54",
              "outline": "#4A4743",
              "outlineVariant": "#D5CFC2",
              "primary": "#1A1918",
              "primaryContainer": "#E8E3D7",
              "secondaryContainer": "#E8E3D7",
              "success": "#1A1918",
              "surface": "#F5F2EB",
              "surfaceContainer": "#F5F2EB",
              "surfaceContainerHigh": "#EFEBE2",
              "surfaceContainerHighest": "#E8E3D7",
              "surfaceContainerLow": "#F1EDE4",
              "warning": "#705516"
            },
            "slate": {
              "buttonShadow": "3px 3px 0 #26272A",
              "error": "#F0A39C",
              "grain": 0.12,
              "grid": "rgba(200,208,216,0.035)",
              "onError": "#0D0D0E",
              "onPrimary": "#0D0D0E",
              "onPrimaryContainer": "#E8EAED",
              "onSecondaryContainer": "#E8EAED",
              "onSurface": "#E8EAED",
              "onSurfaceVariant": "#9A9CA1",
              "outline": "#7E8084",
              "outlineVariant": "#26272A",
              "primary": "#E8EAED",
              "primaryContainer": "#1A1A1D",
              "secondaryContainer": "#1A1A1D",
              "success": "#E8EAED",
              "surface": "#0D0D0E",
              "surfaceContainer": "#0D0D0E",
              "surfaceContainerHigh": "#141416",
              "surfaceContainerHighest": "#1A1A1D",
              "surfaceContainerLow": "#0B0B0C",
              "warning": "#E2C47A"
            }
          }
        },
        "hive": {
          "comb": {
            "breathSeconds": 6.0,
            "cell": 24,
            "frameMs": 33,
            "glowAlphaDark": 0.7,
            "glowAlphaLight": 0.75,
            "lineAlphaDark": 0.27,
            "lineAlphaLight": 0.11,
            "pulseSeconds": 4.5
          },
          "description": "Honey on charcoal (Comb, dark) and on warm wax (Wax, light), with a living honeycomb behind the app that grows from the logo, breathes and pulses. Colors are fixed: no wallpaper, artwork or phone colors.",
          "dynamicColor": false,
          "fonts": {
            "display": "IBM Plex Sans",
            "label": "IBM Plex Sans",
            "mono": "IBM Plex Mono",
            "ui": "IBM Plex Sans"
          },
          "label": {
            "case": "none",
            "size": 12.5,
            "tracking": 0,
            "weight": 600
          },
          "rules": [
            "The installer and uninstaller always use Hive. In the apps it's a choice, offered by a card on Home that returns after an update or a reinstall once dismissed.",
            "The comb holds still with reduced motion, in battery or energy saver, when Animated comb is off, and while the window isn't in front.",
            "The comb is drawn by one shader behind everything; cards stay opaque so text never sits on moving lines.",
            "Primary buttons are honey with a soft honey glow on hover; outlines and focus rings use honey at low alpha."
          ],
          "shape": {
            "full": 999,
            "lg": 18,
            "md": 14,
            "sm": 10,
            "xl": 24,
            "xs": 6
          },
          "variants": {
            "comb": {
              "error": "#F0A39C",
              "onError": "#3B0B07",
              "onPrimary": "#2C1600",
              "onPrimaryContainer": "#FFDDB3",
              "onSecondaryContainer": "#F0E0CC",
              "onSurface": "#EDE6DD",
              "onSurfaceVariant": "#ABA195",
              "outline": "#8D8379",
              "outlineVariant": "#34312D",
              "primary": "#FFB95F",
              "primaryContainer": "#5A3A0E",
              "secondaryContainer": "#2F2A24",
              "success": "#FFB95F",
              "surface": "#121214",
              "surfaceContainer": "#1A1A1D",
              "surfaceContainerHigh": "#212124",
              "surfaceContainerHighest": "#29292D",
              "surfaceContainerLow": "#151517",
              "warning": "#F5D58A"
            },
            "wax": {
              "error": "#9E2B25",
              "onError": "#FFFFFF",
              "onPrimary": "#FFFFFF",
              "onPrimaryContainer": "#2C1600",
              "onSecondaryContainer": "#2A1F14",
              "onSurface": "#1F1B16",
              "onSurfaceVariant": "#5C4F43",
              "outline": "#85766A",
              "outlineVariant": "#DDCBBA",
              "primary": "#8A5100",
              "primaryContainer": "#FFDDB3",
              "secondaryContainer": "#F4E3CD",
              "success": "#8A5100",
              "surface": "#FFF8F3",
              "surfaceContainer": "#F8ECE1",
              "surfaceContainerHigh": "#F3E5D7",
              "surfaceContainerHighest": "#EDDDCD",
              "surfaceContainerLow": "#FCF2E9",
              "warning": "#7A4A00"
            }
          }
        }
      },
      "typography": {
        "body": {
          "lineHeight": 1.5,
          "size": 14,
          "tracking": 0,
          "weight": 400
        },
        "bodySmall": {
          "lineHeight": 1.45,
          "size": 12.5,
          "tracking": 0,
          "weight": 400
        },
        "caption": {
          "lineHeight": 1.3,
          "size": 11,
          "tracking": 0,
          "weight": 500
        },
        "code": {
          "font": "mono",
          "lineHeight": 1.3,
          "size": 13,
          "tracking": 0.14,
          "weight": 700
        },
        "displayLarge": {
          "lineHeight": 1.05,
          "size": 44,
          "tracking": -0.025,
          "weight": 650
        },
        "displaySmall": {
          "lineHeight": 1.1,
          "size": 30,
          "tracking": -0.02,
          "weight": 650
        },
        "headline": {
          "lineHeight": 1.2,
          "size": 22,
          "tracking": -0.015,
          "weight": 650
        },
        "label": {
          "lineHeight": 1.3,
          "size": 12.5,
          "tracking": 0,
          "weight": 600
        },
        "title": {
          "lineHeight": 1.35,
          "size": 16,
          "tracking": 0,
          "weight": 600
        }
      }
    })
}
