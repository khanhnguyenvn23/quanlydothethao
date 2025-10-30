use master
go
create DATABASE quanlythethao
go
use quanlythethao
go

-- =========================
-- 1. B?ng M� Gi?m Gi�
-- =========================
CREATE TABLE MaGiamGia (
    MaMGG INT IDENTITY(1,1) PRIMARY KEY,
    PhanTramGiam FLOAT,
    NgayBatDau DATETIME,
    NgayKetThuc DATETIME,
    TrangThai NVARCHAR(20),
    LoaiGiamGia NVARCHAR(20),
    TrangThaiPhu BIT
);

-- =========================
-- 2. Danh M?c S?n Ph?m
-- =========================
CREATE TABLE DanhMucSanPham (
    MaDM INT IDENTITY(1,1) PRIMARY KEY,
    TenDanhMuc NVARCHAR(55),
    TrangThai BIT,
    SoLuong INT,
    TrangThaiPhu BIT
);

-- =========================
-- 3. M�u
-- =========================
CREATE TABLE Mau (
    MaMau INT IDENTITY(1,1) PRIMARY KEY,
    TenMau NVARCHAR(85),
    TrangThaiPhu BIT
);

-- =========================
-- 4. Size
-- =========================
CREATE TABLE Size (
    MaSize INT IDENTITY(1,1) PRIMARY KEY,
    TenSize NVARCHAR(85),
    TrangThaiPhu BIT
);

-- =========================
-- 5. Nh� Cung C?p
-- =========================
CREATE TABLE NhaCungCap (
    MaNCC INT IDENTITY(1,1) PRIMARY KEY,
    TenNCC NVARCHAR(55),
    DiaChi NVARCHAR(255),
    SDT INT,
    TrangThaiPhu BIT
);

-- =========================
-- 6. S?n Ph?m
-- =========================
CREATE TABLE SanPham (
    MaSP INT IDENTITY(1,1) PRIMARY KEY,
    TenSP NVARCHAR(55),
    SoLuongTon INT,
    DonGia DECIMAL(18,2),
    HinhAnh VARBINARY(MAX),
    TrangThai BIT,
    MaDM INT,
    MaMau INT,
    MaSize INT,
    MaNCC INT,
    TrangThaiPhu BIT
);

-- =========================
-- 7. S?n Ph?m Gi?m Gi�
-- =========================
CREATE TABLE SanPhamGiamGia (
    MaSP INT,
    MaMGG INT,
    TrangThaiPhu BIT
    PRIMARY KEY(MaSP, MaMGG)
    
);

-- =========================
-- 8. Phi?u Nh?p
-- =========================
CREATE TABLE PhieuNhap (
    MaPhieuNhap INT IDENTITY(1,1) PRIMARY KEY,
    NgayNhap DATETIME,
    MaNCC INT,
    TongTien DECIMAL(18,2),
    TrangThaiPhu BIT
);

-- =========================
-- 9. Chi Ti?t Phi?u Nh?p
-- =========================
CREATE TABLE ChiTietPhieuNhap (
    MaPhieuNhap INT,
    MaSP INT,
    SoLuongNhap INT,
    DonGiaNhap DECIMAL(18,2),
    ThanhTien DECIMAL(18,2),
    TrangThaiPhu BIT
    PRIMARY KEY(MaPhieuNhap, MaSP)
);

-- =========================
-- 10. Kh�ch H�ng
-- =========================
CREATE TABLE KhachHang (
    MaKH INT IDENTITY(1,1) PRIMARY KEY,
    HoTen NVARCHAR(55),
    NgaySinh DATETIME,
    SDT INT,
    Email NVARCHAR(100),
    DiaChi NVARCHAR(255),
    TrangThaiPhu BIT
);

-- =========================
-- 11. H�a ??n
-- =========================
CREATE TABLE HoaDon (
    MaHD INT IDENTITY(1,1) PRIMARY KEY,
    MaKH INT,
    MaNV INT,
    TongTien DECIMAL(18,2),
    NgayLap DATETIME,
    TrangThaiPhu BIT
);

-- =========================
-- 12. Chi Ti?t H�a ??n
-- =========================
CREATE TABLE ChiTietHoaDon (
    MaHD INT,
    MaSP INT,
    SoLuong INT,
    DonGia DECIMAL(18,2),
    ThanhTien DECIMAL(18,2),
    TrangThaiPhu BIT
    PRIMARY KEY(MaHD, MaSP)
);

-- =========================
-- 13. Nh�n Vi�n
-- =========================
CREATE TABLE NhanVien (
    MaNV INT IDENTITY(1,1) PRIMARY KEY,
    HoTen NVARCHAR(55),
    ChucVu NVARCHAR(55),
    DiaChi NVARCHAR(255),
    SDT INT,
    MaChiNhanh INT,
    MaTaiKhoan INT,
    TrangThaiPhu BIT
);

-- =========================
-- 14. Chi Nh�nh
-- =========================
CREATE TABLE ChiNhanh (
    MaChiNhanh INT IDENTITY(1,1) PRIMARY KEY,
    TenChiNhanh NVARCHAR(85),
    DiaChi NVARCHAR(255),
    SDT INT,
    TrangThaiPhu BIT
);

-- =========================
-- 15. T�i Kho?n
-- =========================
CREATE TABLE TaiKhoan (
    MaTaiKhoan INT IDENTITY(1,1) PRIMARY KEY,
    TenTaiKhoan NVARCHAR(55),
    NgayTao DATETIME,
    MatKhau NVARCHAR(85),
    TrangThai BIT,
    MaNhomQuyen INT,
    TrangThaiPhu BIT
);

-- =========================
-- 16. Nh�m Quy?n
-- =========================
CREATE TABLE NhomQuyen (
    MaNhomQuyen INT IDENTITY(1,1) PRIMARY KEY,
    TenNhomQuyen NVARCHAR(55),
    TrangThaiPhu BIT
);

-- =========================
-- 17. Ch?c N?ng
-- =========================
CREATE TABLE ChucNang (
    MaChucNang INT IDENTITY(1,1) PRIMARY KEY,
    TenChucNang NVARCHAR(21),
    TrangThaiPhu BIT
);

-- =========================
-- 18. Chi Ti?t Nh�m Quy?n
-- =========================
CREATE TABLE ChiTietNhomQuyen (
    MaNhomQuyen INT,
    MaChucNang INT,
    Them BIT,
    Sua BIT,
    Xoa BIT,
    TrangThaiPhu BIT
    PRIMARY KEY(MaNhomQuyen, MaChucNang)
);

-- =========================
-- R�ng bu?c kh�a ngo?i (ALTER TABLE)
-- =========================

-- SanPham
ALTER TABLE SanPham ADD CONSTRAINT FK_SanPham_DM FOREIGN KEY(MaDM) REFERENCES DanhMucSanPham(MaDM);
ALTER TABLE SanPham ADD CONSTRAINT FK_SanPham_Mau FOREIGN KEY(MaMau) REFERENCES Mau(MaMau);
ALTER TABLE SanPham ADD CONSTRAINT FK_SanPham_Size FOREIGN KEY(MaSize) REFERENCES Size(MaSize);
ALTER TABLE SanPham ADD CONSTRAINT FK_SanPham_NCC FOREIGN KEY(MaNCC) REFERENCES NhaCungCap(MaNCC);

-- SanPhamGiamGia
ALTER TABLE SanPhamGiamGia ADD CONSTRAINT FK_SPGG_SP FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP);
ALTER TABLE SanPhamGiamGia ADD CONSTRAINT FK_SPGG_MGG FOREIGN KEY(MaMGG) REFERENCES MaGiamGia(MaMGG);

-- PhieuNhap
ALTER TABLE PhieuNhap ADD CONSTRAINT FK_PhieuNhap_NCC FOREIGN KEY(MaNCC) REFERENCES NhaCungCap(MaNCC);

-- ChiTietPhieuNhap
ALTER TABLE ChiTietPhieuNhap ADD CONSTRAINT FK_CTPN_PN FOREIGN KEY(MaPhieuNhap) REFERENCES PhieuNhap(MaPhieuNhap);
ALTER TABLE ChiTietPhieuNhap ADD CONSTRAINT FK_CTPN_SP FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP);

-- HoaDon
ALTER TABLE HoaDon ADD CONSTRAINT FK_HoaDon_KH FOREIGN KEY(MaKH) REFERENCES KhachHang(MaKH);
ALTER TABLE HoaDon ADD CONSTRAINT FK_HoaDon_NV FOREIGN KEY(MaNV) REFERENCES NhanVien(MaNV);

-- ChiTietHoaDon
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FK_CTHD_HD FOREIGN KEY(MaHD) REFERENCES HoaDon(MaHD);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FK_CTHD_SP FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP);

-- NhanVien
ALTER TABLE NhanVien ADD CONSTRAINT FK_NV_CN FOREIGN KEY(MaChiNhanh) REFERENCES ChiNhanh(MaChiNhanh);
ALTER TABLE NhanVien ADD CONSTRAINT FK_NV_TK FOREIGN KEY(MaTaiKhoan) REFERENCES TaiKhoan(MaTaiKhoan);

-- TaiKhoan
ALTER TABLE TaiKhoan ADD CONSTRAINT FK_TK_NQ FOREIGN KEY(MaNhomQuyen) REFERENCES NhomQuyen(MaNhomQuyen);

-- ChiTietNhomQuyen
ALTER TABLE ChiTietNhomQuyen ADD CONSTRAINT FK_CTNQ_NQ FOREIGN KEY(MaNhomQuyen) REFERENCES NhomQuyen(MaNhomQuyen);
ALTER TABLE ChiTietNhomQuyen ADD CONSTRAINT FK_CTNQ_CN FOREIGN KEY(MaChucNang) REFERENCES ChucNang(MaChucNang);

ALTER TABLE phieunhap
ADD idnv INT;
ALTER TABLE phieunhap
ADD CONSTRAINT FK_PhieuNhap_NhanVien
FOREIGN KEY (idnv) REFERENCES nhanvien(MANV);

--chỉnh thuộc tính hình ảnh trong database về thành chuỗi lưu link ảnh
ALTER TABLE SanPham
ALTER COLUMN HinhAnh NVARCHAR(255);

ALTER TABLE KhachHang
ALTER COLUMN SDT VARCHAR(15)