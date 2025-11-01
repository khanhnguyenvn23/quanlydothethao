package DTO;

public class Size_DTO {
    private int maSize;
    private String tenSize;

    public Size_DTO(int i) {
            
    }

    public Size_DTO(int maSize, String tenSize) {
        this.maSize = maSize;
        this.tenSize = tenSize;
    }

    public int getMaSize() {
        return maSize;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

}