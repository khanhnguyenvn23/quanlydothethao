-- Cập nhật bảng PhieuNhap để thêm cột MaNV (Mã Nhân Viên)
USE quanlythethao;
GO

-- Thêm cột MaNV vào bảng PhieuNhap
ALTER TABLE PhieuNhap 
ADD MaNV INT;

-- Thêm ràng buộc khóa ngoại cho MaNV
ALTER TABLE PhieuNhap 
ADD CONSTRAINT FK_PhieuNhap_NV 
FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV);

-- Cập nhật dữ liệu mẫu (gán nhân viên mặc định cho các phiếu nhập hiện có)
UPDATE PhieuNhap 
SET MaNV = 1 
WHERE MaNV IS NULL;

SELECT * FROM PhieuNhap;