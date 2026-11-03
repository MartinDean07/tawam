# Tawam: what changed from NectarLink

Tawam (توأم, "twin") is a modified version of
[NectarLink](https://github.com/thtbee/nectarlink) by Bee and the NectarLink
contributors. It is **not** the official NectarLink and is not endorsed by
that project. It is based on NectarLink 0.1.4 (commit `feee39a`).

Both the **desktop app** and the **Android app (APK)** are modified.

## Security changes

| Change | Desktop | Android |
|---|---|---|
| Self-updater removed from release builds. Upstream's updater downloads from GitHub and installs (as root/administrator on the PC), trusting only a SHA-256 list from the same release, with no signature. Update by installing a newer release yourself; Android still checks the APK signature. | Yes (`--features self-update` to restore) | Yes (`-Pself.update=true` to restore) |
| LocalSend compatibility is HTTPS-only. Plain-HTTP LocalSend connections, announcements and transfers are refused in both directions, and a peer's certificate must match the fingerprint it announces. | Yes | Yes (shared Rust core) |
| The `.deb` package declares every library it needs (`libegl1`, `libglib2.0`, `libgssapi-krb5-2`). | Yes | — |

## Rebranding (required by NectarLink's trademark policy)

- Name: NectarLink → Tawam / توأم. New logo (`assets/brand/`).
- Android application ID: `app.nectarlink` → `no.martindean.tawam`. Tawam
  installs next to NectarLink and is signed with a different key.
- Pairing links: `nectarlink://` → `tawam://`. The network protocol itself is
  unchanged.
- Linux binary and package: `nectarlink-desktop` → `tawam-desktop`, package `tawam`.
- Internal code names (Rust crate names, Kotlin package `app.nectarlink.android`,
  the QML module) are unchanged so that upstream fixes still merge cleanly.

## Licenses

Unchanged, see `LICENSE`. The apps are GPL-3.0-or-later (with
`LICENSES/APP-STORE-EXCEPTION.md`), the core libraries MPL-2.0, and the docs
CC-BY-4.0. The full source for every Tawam release is in this repository at
the release's tag.
