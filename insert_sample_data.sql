-- =====================================================
-- FILE: INSERT SAMPLE DATA FOR QUAN LY THE THAO
-- Mô tả: Chèn dữ liệu mẫu cho tất cả các bảng
-- =====================================================

USE quanlythethao;
GO

-- =====================================================
-- 1. NHÓM QUYỀN (Phải insert trước vì có khóa ngoại)
-- =====================================================
INSERT INTO NhomQuyen (TenNhomQuyen, TrangThaiPhu) VALUES 
(N'Quản trị viên', 1),
(N'Nhân viên bán hàng', 1),
(N'Nhân viên kho', 1),
(N'Thủ kho', 1);

-- =====================================================
-- 2. CHỨC NĂNG
-- =====================================================
INSERT INTO ChucNang (TenChucNang, TrangThaiPhu) VALUES 
(N'Quản lý sản phẩm', 1),
(N'Quản lý phiếu nhập', 1),
(N'Quản lý hóa đơn', 1),
(N'Quản lý khách hàng', 1),
(N'Quản lý nhân viên', 1),
(N'Báo cáo thống kê', 1);

-- =====================================================
-- 3. CHI NHÁNH
-- =====================================================
INSERT INTO ChiNhanh (TenChiNhanh, DiaChi, SDT, TrangThaiPhu) VALUES 
(N'Chi nhánh Quận 1', N'123 Nguyễn Huệ, Quận 1, TP.HCM', 0283456789, 1),
(N'Chi nhánh Quận 3', N'456 Lê Văn Sỹ, Quận 3, TP.HCM', 0283456790, 1),
(N'Chi nhánh Thủ Đức', N'789 Võ Văn Ngân, Thủ Đức, TP.HCM', 0283456791, 1);

-- =====================================================
-- 4. TÀI KHOẢN
-- =====================================================
INSERT INTO TaiKhoan (TenTaiKhoan, NgayTao, MatKhau, TrangThai, MaNhomQuyen, TrangThaiPhu) VALUES 
(N'admin', '2024-01-01', N'123456', 1, 1, 1),
(N'nhanvien01', '2024-01-02', N'123456', 1, 2, 1),
(N'nhanvien02', '2024-01-03', N'123456', 1, 2, 1),
(N'thukho01', '2024-01-04', N'123456', 1, 4, 1);

-- =====================================================
-- 5. NHÂN VIÊN
-- =====================================================
INSERT INTO NhanVien (HoTen, ChucVu, DiaChi, SDT, MaChiNhanh, MaTaiKhoan, TrangThaiPhu) VALUES 
(N'Nguyễn Văn Admin', N'Quản trị viên', N'123 Lê Lợi, Quận 1, TP.HCM', 0901234567, 1, 1, 1),
(N'Trần Thị Hoa', N'Nhân viên bán hàng', N'456 Trần Hưng Đạo, Quận 1, TP.HCM', 0901234568, 1, 2, 1),
(N'Lê Văn Nam', N'Nhân viên bán hàng', N'789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', 0901234569, 2, 3, 1),
(N'Phạm Thị Lan', N'Thủ kho', N'321 Lê Văn Việt, Thủ Đức, TP.HCM', 0901234570, 3, 4, 1);

-- =====================================================
-- 6. NHÀ CUNG CÂP
-- =====================================================
INSERT INTO NhaCungCap (TenNCC, DiaChi, SDT, TrangThaiPhu) VALUES 
(N'Nike Vietnam', N'Tầng 10, Tòa nhà Bitexco, Quận 1, TP.HCM', 0283456001, 1),
(N'Adidas Vietnam', N'Tầng 15, Landmark 81, Bình Thạnh, TP.HCM', 0283456002, 1),
(N'Puma Vietnam', N'Tầng 8, Vincom Center, Quận 3, TP.HCM', 0283456003, 1),
(N'Converse Vietnam', N'Tầng 5, Diamond Plaza, Quận 1, TP.HCM', 0283456004, 1),
(N'Vans Vietnam', N'Tầng 12, Saigon Centre, Quận 1, TP.HCM', 0283456005, 1);

-- =====================================================
-- 7. DANH MỤC SẢN PHẨM
-- =====================================================
INSERT INTO DanhMucSanPham (TenDanhMuc, TrangThai, SoLuong, TrangThaiPhu) VALUES 
(N'Giày thể thao', 1, 0, 1),
(N'Áo thể thao', 1, 0, 1),
(N'Quần thể thao', 1, 0, 1),
(N'Phụ kiện thể thao', 1, 0, 1),
(N'Dụng cụ tập luyện', 1, 0, 1);

-- =====================================================
-- 8. MÀU SẮC
-- =====================================================
INSERT INTO Mau (TenMau, TrangThaiPhu) VALUES 
(N'Đen', 1),
(N'Trắng', 1),
(N'Đỏ', 1),
(N'Xanh dương', 1),
(N'Xanh lá', 1),
(N'Vàng', 1),
(N'Hồng', 1),
(N'Xám', 1);

-- =====================================================
-- 9. SIZE
-- =====================================================
INSERT INTO Size (TenSize, TrangThaiPhu) VALUES 
(N'35', 1),
(N'36', 1),
(N'37', 1),
(N'38', 1),
(N'39', 1),
(N'40', 1),
(N'41', 1),
(N'42', 1),
(N'43', 1),
(N'44', 1),
(N'S', 1),
(N'M', 1),
(N'L', 1),
(N'XL', 1),
(N'XXL', 1);
-- =====================================================
-- 10. SẢN PHẨM
-- =====================================================

-- Giày Nike
INSERT INTO SanPham (TenSP,HinhAnh,SoLuongTon, DonGia, TrangThai, MaDM, MaMau, MaSize, MaNCC, TrangThaiPhu) VALUES
(N'Nike Air Max 270','giay1',50, 2500000, 1, 1, 1, 6, 1, 1),
(N'Nike Air Force 1','giay2', 45, 2200000, 1, 1, 2, 7, 1, 1),
(N'Nike Revolution 5', 'giay3',60, 1800000, 1, 1, 3, 8, 1, 1);

-- Giày Adidas
INSERT INTO SanPham (TenSP,HinhAnh,SoLuongTon, DonGia, TrangThai, MaDM, MaMau, MaSize, MaNCC, TrangThaiPhu) VALUES
(N'Adidas Ultraboost 22', 'giay4', 40, 3200000, 1, 1, 4, 6, 2, 1),
(N'Adidas Stan Smith', 'giay5', 55, 2100000, 1, 1, 2, 7, 2, 1),
(N'Adidas Superstar', 'giay6', 35, 2400000, 1, 1, 1, 8, 2, 1);

-- Áo thể thao
INSERT INTO SanPham (TenSP,HinhAnh,SoLuongTon, DonGia, TrangThai, MaDM, MaMau, MaSize, MaNCC, TrangThaiPhu) VALUES
(N'Áo Nike Dri-FIT','ao1', 80, 650000, 1, 2, 1, 12, 1, 1),
(N'Áo Adidas 3-Stripes','ao2', 75, 580000, 1, 2, 4, 13, 2, 1),
(N'Áo Puma Essential', 'ao3',90, 520000, 1, 2, 3, 11, 3, 1);

-- Quần thể thao
INSERT INTO SanPham (TenSP,HinhAnh,SoLuongTon, DonGia, TrangThai, MaDM, MaMau, MaSize, MaNCC, TrangThaiPhu) VALUES
(N'Quần Nike Sportswear','quan1', 65, 850000, 1, 3, 1, 13, 1, 1),
(N'Quần Adidas Essentials', 'quan2',70, 780000, 1, 3, 8, 12, 2, 1);

INSERT INTO PhieuNhap (NgayNhap, MaNCC, idnv, TongTien, TrangThaiPhu) VALUES 
('2024-01-15 09:30:00', 1, 4, 15000000, 1),
('2024-01-20 14:15:00', 2, 4, 12500000, 1),
('2024-02-05 10:45:00', 3, 4, 8750000, 1),
('2024-02-18 16:20:00', 1, 4, 11200000, 1),
('2024-03-10 11:30:00', 2, 4, 9800000, 1);


-- =====================================================
-- 12. CHI TIẾT PHIẾU NHẬP
-- =====================================================
INSERT INTO ChiTietPhieuNhap (MaPhieuNhap, MaSP, SoLuongNhap, DonGiaNhap, ThanhTien, TrangThaiPhu) VALUES 
-- Phiếu nhập 1 - Tổng: 15,000,000
(1, 1, 10, 800000, 8000000, 1),
(1, 2, 7, 1000000, 7000000, 1),

-- Phiếu nhập 2 - Tổng: 12,500,000
(2, 4, 5, 1500000, 7500000, 1),
(2, 5, 10, 500000, 5000000, 1),

-- Phiếu nhập 3 - Tổng: 8,750,000
(3, 9, 15, 250000, 3750000, 1),
(3, 6, 5, 1000000, 5000000, 1),

-- Phiếu nhập 4 - Tổng: 11,200,000
(4, 3, 8, 800000, 6400000, 1),
(4, 8, 12, 400000, 4800000, 1),

-- Phiếu nhập 5 - Tổng: 9,800,000
(5, 10, 10, 500000, 5000000, 1),
(5, 7, 8, 600000, 4800000, 1);

-- =====================================================
-- 13. KHÁCH HÀNG
-- =====================================================
INSERT INTO KhachHang (HoTen, NgaySinh, SDT, Email, DiaChi, TrangThaiPhu) VALUES 
(N'Nguyễn Văn Khách', '1990-05-15', '0912345678', 'khach01@email.com', N'123 Lê Lợi, Quận 1, TP.HCM', 1),
(N'Trần Thị Mai', '1985-08-22', '0912345679', 'mai.tran@email.com', N'456 Nguyễn Huệ, Quận 1, TP.HCM', 1),
(N'Lê Hoàng Nam', '1992-12-10', '0912345680', 'nam.le@email.com', N'789 Đồng Khởi, Quận 1, TP.HCM', 1),
(N'Phạm Thị Lan', '1988-03-25', '0912345681', 'lan.pham@email.com', N'321 Hai Bà Trưng, Quận 3, TP.HCM', 1),
(N'Võ Minh Tuấn', '1995-07-18', '0912345682', 'tuan.vo@email.com', N'654 Lê Văn Sỹ, Quận 3, TP.HCM', 1);

-- =====================================================
-- 14. HÓA ĐƠN
-- =====================================================
INSERT INTO HoaDon (MaKH, MaNV, TongTien, NgayLap, TrangThaiPhu) VALUES 
(1, 2, 5200000, '2024-03-15 10:30:00', 1),
(2, 2, 3400000, '2024-03-16 14:15:00', 1),
(3, 3, 2800000, '2024-03-17 09:45:00', 1),
(4, 2, 4600000, '2024-03-18 16:20:00', 1),
(5, 3, 1950000, '2024-03-19 11:30:00', 1);

-- =====================================================
-- 15. CHI TIẾT HÓA ĐƠN
-- =====================================================
INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia, ThanhTien, TrangThaiPhu) VALUES 
-- Hóa đơn 1
(1, 1, 2, 2500000, 5000000, 1),
(1, 7, 1, 650000, 650000, 1),

-- Hóa đơn 2
(2, 2, 1, 2200000, 2200000, 1),
(2, 8, 2, 580000, 1160000, 1),

-- Hóa đơn 3  
(3, 5, 1, 2100000, 2100000, 1),
(3, 9, 1, 520000, 520000, 1),

-- Hóa đơn 4
(4, 4, 1, 3200000, 3200000, 1),
(4, 10, 2, 850000, 1700000, 1),

-- Hóa đơn 5
(5, 3, 1, 1800000, 1800000, 1);

-- =====================================================
-- 16. MÃ GIẢM GIÁ
-- =====================================================
INSERT INTO MaGiamGia (PhanTramGiam, NgayBatDau, NgayKetThuc, TrangThai, LoaiGiamGia, TrangThaiPhu) VALUES 
(10.0, '2024-01-01', '2024-12-31', N'Đang áp dụng', N'Giảm giá thường', 1),
(15.0, '2024-03-01', '2024-03-31', N'Đang áp dụng', N'Giảm giá tháng 3', 1),
(20.0, '2024-06-01', '2024-06-30', N'Chưa áp dụng', N'Giảm giá hè', 1),
(25.0, '2024-12-01', '2024-12-31', N'Chưa áp dụng', N'Giảm giá cuối năm', 1);

-- =====================================================
-- 17. CHI TIẾT NHÓM QUYỀN
-- =====================================================
INSERT INTO ChiTietNhomQuyen (MaNhomQuyen, MaChucNang, Them, Sua, Xoa, TrangThaiPhu) VALUES 
-- Quản trị viên - Full quyền
(1, 1, 1, 1, 1, 1),
(1, 2, 1, 1, 1, 1),
(1, 3, 1, 1, 1, 1),
(1, 4, 1, 1, 1, 1),
(1, 5, 1, 1, 1, 1),
(1, 6, 1, 1, 1, 1),

-- Nhân viên bán hàng - Hạn chế
(2, 1, 0, 0, 0, 1),
(2, 3, 1, 1, 0, 1),
(2, 4, 1, 1, 0, 1),

-- Thủ kho - Quản lý kho
(4, 1, 1, 1, 0, 1),
(4, 2, 1, 1, 0, 1);

-- =====================================================
-- HOÀN THÀNH CHÈN DỮ LIỆU MẪU
-- =====================================================
PRINT N'Đã chèn dữ liệu mẫu thành công!';
PRINT N'- Nhóm quyền: 4 bản ghi';
PRINT N'- Chức năng: 6 bản ghi';  
PRINT N'- Chi nhánh: 3 bản ghi';
PRINT N'- Tài khoản: 4 bản ghi';
PRINT N'- Nhân viên: 4 bản ghi';
PRINT N'- Nhà cung cấp: 5 bản ghi';
PRINT N'- Danh mục: 5 bản ghi';
PRINT N'- Màu sắc: 8 bản ghi';
PRINT N'- Size: 15 bản ghi';
PRINT N'- Sản phẩm: 10 bản ghi';
PRINT N'- Phiếu nhập: 5 bản ghi';
PRINT N'- Chi tiết phiếu nhập: 10 bản ghi';
PRINT N'- Khách hàng: 5 bản ghi';
PRINT N'- Hóa đơn: 5 bản ghi';
PRINT N'- Chi tiết hóa đơn: 8 bản ghi';
PRINT N'- Mã giảm giá: 4 bản ghi';
PRINT N'- Chi tiết nhóm quyền: 9 bản ghi';

SELECT 'Database đã sẵn sàng để test ứng dụng!' AS ThongBao;