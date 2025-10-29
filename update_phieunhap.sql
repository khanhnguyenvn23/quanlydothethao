ALTER TABLE phieunhap
ADD idnv INT;
ALTER TABLE phieunhap
ADD CONSTRAINT FK_PhieuNhap_NhanVien
FOREIGN KEY (idnv) REFERENCES nhanvien(MANV);