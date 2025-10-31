ALTER TABLE DanhMucSanPham
DROP COLUMN TrangThaiPhu,SoLuong;

ALTER TABLE DanhMucSanPham
ADD mota NVARCHAR(MAX);

update DanhMucSanPham set mota=N'đây là giày thể thao' where MaDM=1;
update DanhMucSanPham set mota=N'đây là áo thao' where MaDM=2;
update DanhMucSanPham set mota=N'đây là quần thao' where MaDM=3;
update DanhMucSanPham set mota=N'đây là phụ kiện thể thao' where MaDM=4;
update DanhMucSanPham set mota=N'đây là dụng cụ thể thao' where MaDM=5;