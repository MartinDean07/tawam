// SPDX-License-Identifier: GPL-3.0-or-later
import QtQuick
import app.nectarlink

// Tawam pairing: tabs for "QR code" and "Nearby", a framed QR code next to
// numbered steps with a countdown bar, the code check as one "123 456"
// strip, and the outcome. While `running`, this PC is in pairing mode.
Item {
    id: panel
    property bool running: false
    property bool cancellable: true
    property bool compact: false
    signal finished
    signal cancelled

    property bool showNearby: false
    readonly property string state_: Pairing.state
    readonly property bool waiting: state_ === "hosting" || state_ === "starting" || state_ === "idle"

    implicitHeight: content.implicitHeight

    onRunningChanged: {
        showNearby = false
        Pairing.setNearbyShown(running)
        if (running && state_ !== "comparing" && state_ !== "confirmed" && state_ !== "connecting")
            Pairing.start()
        else if (!running)
            stopHosting()
    }
    Component.onCompleted: {
        Pairing.setNearbyShown(running)
        if (running && state_ !== "comparing" && state_ !== "confirmed" && state_ !== "connecting")
            Pairing.start()
    }
    Component.onDestruction: {
        Pairing.setNearbyShown(false)
        stopHosting()
    }

    function stopHosting() {
        if (state_ === "hosting")
            Pairing.cancel()
    }

    Timer {
        interval: 1400
        running: panel.state_ === "paired" && panel.running
        onTriggered: panel.finished()
    }

    property int secondsLeft: 0
    property int secondsTotal: 0
    Timer {
        interval: 1000
        repeat: true
        triggeredOnStart: true
        running: panel.running && panel.state_ === "hosting"
        onTriggered: {
            panel.secondsLeft = Math.max(0, Math.round((Pairing.expiresAt - Date.now()) / 1000))
            if (panel.secondsLeft > panel.secondsTotal)
                panel.secondsTotal = panel.secondsLeft
        }
    }

    // A numbered step: a small outlined square with the number, then the text.
    component Step: Row {
        property int number
        property string label
        spacing: 10
        Rectangle {
            width: 22; height: 22; radius: 4
            color: "transparent"
            border.width: 1.5
            border.color: Theme.primary
            Txt { anchors.centerIn: parent; text: parent.parent.number; role: "caption"; weight: 700; color: Theme.primary }
        }
        Txt {
            anchors.verticalCenter: parent.verticalCenter
            width: Math.max(80, (parent.width > 0 ? parent.width : 220) - 32)
            text: label
            role: "body"
            wrapMode: Text.WordWrap
            elide: Text.ElideNone
        }
    }

    // The QR code inside a thin frame with accent corner marks.
    component FramedQr: Item {
        property int side: 196
        width: side + 28; height: side + 28
        Rectangle {
            anchors.fill: parent
            radius: Theme.radiusMd
            color: "#FFFFFF"
            border.width: 1
            border.color: Theme.outlineVariant
        }
        Repeater {
            model: 4
            delegate: Item {
                required property int index
                width: 18; height: 18
                x: index % 2 === 0 ? 4 : parent.width - 22
                y: index < 2 ? 4 : parent.height - 22
                Rectangle { width: 18; height: 3; radius: 1.5; color: Theme.primary; y: parent.index < 2 ? 0 : 15 }
                Rectangle { width: 3; height: 18; radius: 1.5; color: Theme.primary; x: parent.index % 2 === 0 ? 0 : 15 }
            }
        }
        QrCode {
            anchors.centerIn: parent
            width: parent.side; height: parent.side
            visible: panel.state_ === "hosting"
            path: Pairing.qrPath
            modules: Pairing.qrSize
            Accessible.role: Accessible.Graphic
            Accessible.name: qsTr("Pairing QR code")
        }
        Spinner {
            anchors.centerIn: parent
            visible: panel.state_ !== "hosting"
            width: 28; height: 28
        }
    }

    Column {
        id: content
        width: parent.width
        spacing: panel.compact ? 12 : 18

        // ---- Header: eyebrow + title ----
        Column {
            width: parent.width
            spacing: 4
            Txt {
                text: qsTr("TAWAM · PAIRING")
                role: "caption"
                weight: 700
                color: Theme.primary
                font.letterSpacing: 1.4
            }
            Txt {
                width: parent.width
                role: "displaySmall"
                size: 24
                wrapMode: Text.WordWrap
                text: {
                    switch (panel.state_) {
                    case "comparing": return qsTr("Confirm the code")
                    case "paired": return qsTr("Your twin is ready")
                    case "failed": return qsTr("Pairing didn't work")
                    default: return qsTr("Connect your phone")
                    }
                }
            }
        }

        // ---- Tabs ----
        Segmented {
            visible: panel.waiting
            options: [
                { value: "qr", label: qsTr("QR code") },
                { value: "nearby", label: qsTr("Nearby") }
            ]
            value: panel.showNearby ? "nearby" : "qr"
            onPicked: (value) => panel.showNearby = value === "nearby"
        }

        // ---- QR code + steps ----
        Row {
            width: parent.width
            spacing: 24
            visible: panel.waiting && !panel.showNearby
            FramedQr {
                id: qr
                side: panel.compact ? 150 : 196
                anchors.verticalCenter: parent.verticalCenter
            }
            Column {
                width: Math.max(120, parent.width - qr.width - parent.spacing)
                anchors.verticalCenter: parent.verticalCenter
                spacing: 14
                Step { width: parent.width; number: 1; label: qsTr("Open Tawam on your phone") }
                Step { width: parent.width; number: 2; label: qsTr("Choose “Scan QR”") }
                Step { width: parent.width; number: 3; label: qsTr("Point the camera at this code") }
                Column {
                    width: parent.width
                    spacing: 6
                    visible: panel.state_ === "hosting"
                    Rectangle {
                        width: parent.width; height: 4; radius: 2
                        color: Theme.surfaceContainerHighest
                        Rectangle {
                            height: parent.height; radius: 2
                            color: Theme.primary
                            width: panel.secondsTotal > 0 ? parent.width * panel.secondsLeft / panel.secondsTotal : parent.width
                        }
                    }
                    Txt {
                        width: parent.width
                        wrapMode: Text.WordWrap
                        role: "caption"
                        muted: true
                        text: qsTr("New code in %1:%2 · same Wi-Fi only")
                              .arg(Math.floor(panel.secondsLeft / 60)).arg(("0" + panel.secondsLeft % 60).slice(-2))
                    }
                }
            }
        }

        // ---- Nearby devices ----
        Column {
            width: parent.width
            spacing: 8
            visible: panel.waiting && panel.showNearby
            Txt {
                width: parent.width
                wrapMode: Text.WordWrap
                role: "body"
                muted: true
                text: qsTr("On your phone, open Tawam and choose “Nearby”. Phones on this Wi-Fi appear below.")
            }
            Repeater {
                model: Pairing.nearby
                delegate: Rectangle {
                    required property var modelData
                    width: parent.width
                    height: 60
                    radius: Theme.radiusMd
                    color: "transparent"
                    border.width: 1
                    border.color: Theme.outlineVariant
                    Rectangle {
                        width: 3; radius: 1.5
                        anchors.left: parent.left; anchors.leftMargin: 8
                        anchors.top: parent.top; anchors.bottom: parent.bottom
                        anchors.topMargin: 14; anchors.bottomMargin: 14
                        color: Theme.primary
                    }
                    Icon {
                        id: deviceIcon
                        anchors.left: parent.left; anchors.leftMargin: 22
                        anchors.verticalCenter: parent.verticalCenter
                        width: 22; height: 22
                        path: Icons.phone
                    }
                    Txt {
                        anchors.left: deviceIcon.right; anchors.leftMargin: 12
                        anchors.right: pairButton.left; anchors.rightMargin: 12
                        anchors.verticalCenter: parent.verticalCenter
                        text: modelData.name
                        role: "title"
                        elide: Text.ElideRight
                    }
                    Button {
                        id: pairButton
                        anchors.right: parent.right; anchors.rightMargin: 12
                        anchors.verticalCenter: parent.verticalCenter
                        size: "sm"
                        text: qsTr("Connect")
                        onClicked: Pairing.pairNearby(modelData.id)
                    }
                }
            }
            Row {
                spacing: 8
                visible: Pairing.nearby.length === 0
                Spinner { width: 16; height: 16; anchors.verticalCenter: parent.verticalCenter }
                Txt { text: qsTr("Searching this network…"); role: "bodySmall"; muted: true }
            }
        }

        // ---- Working ----
        Row {
            spacing: 10
            visible: panel.state_ === "connecting" || panel.state_ === "confirmed"
            Spinner { width: 18; height: 18; anchors.verticalCenter: parent.verticalCenter }
            Txt {
                anchors.verticalCenter: parent.verticalCenter
                role: "body"
                text: panel.state_ === "connecting"
                      ? qsTr("Connecting to %1…").arg(Pairing.peerName)
                      : qsTr("Waiting for %1 to confirm…").arg(Pairing.peerName)
            }
        }

        // ---- Code check: one strip, "123 456" ----
        Column {
            width: parent.width
            spacing: 16
            visible: panel.state_ === "comparing" || panel.state_ === "confirmed"
            Txt {
                width: parent.width
                wrapMode: Text.WordWrap
                role: "body"
                muted: true
                text: qsTr("%1 should show exactly this code. If it does, confirm. This stops anyone else from slipping in.")
                      .arg(Pairing.peerName.length > 0 ? Pairing.peerName : qsTr("Your phone"))
            }
            Rectangle {
                width: codeText.implicitWidth + 48
                height: 64
                radius: Theme.radiusMd
                color: Theme.primaryContainer
                Txt {
                    id: codeText
                    anchors.centerIn: parent
                    role: "code"
                    size: 32
                    weight: 600
                    color: Theme.primaryContainerContent
                    font.letterSpacing: 4
                    text: Pairing.code.length === 6 ? Pairing.code.slice(0, 3) + " " + Pairing.code.slice(3) : Pairing.code
                    Accessible.name: Pairing.code.split("").join(" ")
                }
            }
            Row {
                spacing: 10
                visible: panel.state_ === "comparing"
                Button { text: qsTr("Same code, connect"); iconPath: Icons.check; onClicked: Pairing.confirm(true) }
                Button { variant: "outline"; text: qsTr("Different code"); onClicked: Pairing.confirm(false) }
            }
        }

        // ---- Outcome ----
        Row {
            width: parent.width
            spacing: 14
            visible: panel.state_ === "paired" || panel.state_ === "failed"
            Rectangle {
                width: 44; height: 44; radius: Theme.radiusMd
                color: panel.state_ === "paired" ? Theme.primary : Theme.errorContainer
                Icon {
                    anchors.centerIn: parent
                    width: 22; height: 22
                    path: panel.state_ === "paired" ? Icons.check : Icons.warning
                    color: panel.state_ === "paired" ? Theme.primaryContent : Theme.error
                }
            }
            Txt {
                width: parent.width - 58
                anchors.verticalCenter: parent.verticalCenter
                wrapMode: Text.WordWrap
                role: "body"
                text: {
                    if (panel.state_ === "paired")
                        return qsTr("%1 and this PC are now twins.").arg(Pairing.peerName)
                    switch (Pairing.failure) {
                    case "rejected": return qsTr("Pairing was cancelled or the codes didn't match, so nothing was paired.")
                    case "declined": return qsTr("Pairing was declined on one of the devices.")
                    case "unreachable": return qsTr("Couldn't reach the phone. Check that both are on the same Wi-Fi and the firewall allows Tawam.")
                    case "expired": return qsTr("The code expired.")
                    default: return qsTr("Something went wrong.")
                    }
                }
            }
        }
        Button {
            visible: panel.state_ === "failed"
            text: qsTr("Try again")
            iconPath: Icons.refresh
            onClicked: { panel.showNearby = false; Pairing.start() }
        }

        Button {
            visible: panel.cancellable && panel.state_ !== "paired"
            variant: "text"
            text: qsTr("Cancel")
            onClicked: panel.cancelled()
        }
    }
}
