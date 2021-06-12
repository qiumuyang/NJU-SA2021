import sys
from PyQt5.QtWidgets import (
    QApplication,
    QMainWindow,
    QTableWidget,
    QTableWidgetItem,
    QVBoxLayout,
    QHBoxLayout,
    QWidget,
    QMessageBox,
    QPushButton,
    QFileDialog,
    QInputDialog,
    QLabel,
    QAbstractItemView,
)
from PyQt5.QtGui import QFont
from PyQt5.QtCore import pyqtSignal, Qt
from upload import upload_file, get_video_list


class ClickLabel(QLabel):
    clicked = pyqtSignal()

    def mousePressEvent(self, event):
        if event.button() == Qt.LeftButton:
            self.clicked.emit()

    def enterEvent(self, event):
        self.setCursor(Qt.PointingHandCursor)

    def leaveEvent(self, event):
        self.setCursor(Qt.ArrowCursor)


class MainWindow(QMainWindow):
    Name_Label_Init = "Login"

    def __init__(self) -> None:
        super().__init__()

        self.table_widget = QTableWidget(self)
        self.table_widget.setEditTriggers(QAbstractItemView.NoEditTriggers)
        self.table_widget.setColumnCount(3)
        self.table_widget.setHorizontalHeaderLabels(["VID", "Author", "Title"])
        self.update_video_list()

        self.upload_button = QPushButton("upload")
        self.name_label = ClickLabel("1")
        font = QFont()
        font.setUnderline(True)
        self.name_label.setFont(font)

        self.hbox_layout = QHBoxLayout()
        self.hbox_layout.addWidget(self.upload_button)
        self.hbox_layout.addStretch(2)
        self.hbox_layout.addWidget(self.name_label)

        self.vbox_layout = QVBoxLayout()
        self.vbox_layout.addLayout(self.hbox_layout)
        self.vbox_layout.addWidget(self.table_widget)
        self.central_widget = QWidget()
        self.central_widget.setLayout(self.vbox_layout)
        self.setCentralWidget(self.central_widget)

        self.upload_button.pressed.connect(self.upload_video)
        self.name_label.clicked.connect(self.set_uid)

    def set_uid(self):
        ret, suc = QInputDialog.getInt(
            self, "UID", "Enter your UID:", value=0, min=1)
        if not suc:
            return
        self.name_label.setText(str(ret))

    def upload_video(self):
        uid = self.name_label.text()
        if uid == self.Name_Label_Init:
            QMessageBox.warning(self, "Warning", "UID should not be empty")
            return
        uid = int(uid)

        title, suc = QInputDialog.getText(
            self, "Title", "Enter title of your video:")
        if not suc:
            return
        title = title.strip()
        if not title:
            QMessageBox.warning(
                self, "Warning", "Video Title should not be empty")
            return

        file, type = QFileDialog.getOpenFileName(self, "Choose Video", ".",
                                                 "Videos (*.mp4 *.avi)")
        if not file:
            return
        try:
            code = upload_file(uid, title, file)
        except:
            QMessageBox.critical(self, "Error", "Upload failed")
        else:
            if code == 200:
                QMessageBox.information(self, "Success", "Upload success")
                self.update_video_list()
            else:
                QMessageBox.critical(self, "Error", f"Upload failed <{code}>")

    def update_video_list(self):
        videos = get_video_list()
        self.table_widget.setRowCount(len(videos))
        col = self.table_widget.columnCount()
        for i, video in enumerate(videos):
            for j, item in enumerate(video.values()):
                if j >= col:
                    break
                self.table_widget.setItem(i, j, QTableWidgetItem(str(item)))


if __name__ == '__main__':
    app = QApplication(sys.argv)
    mw = MainWindow()
    mw.show()
    sys.exit(app.exec_())
