package com.data.pojo.response;

public class CarMediaDTO {
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;
    private String videoUrl;
    private String vinReport;

    public CarMediaDTO() {}

    public CarMediaDTO(String photo1, String photo2, String photo3, String photo4,
                       String photo5, String videoUrl, String vinReport) {
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
        this.videoUrl = videoUrl;
        this.vinReport = vinReport;
    }

    // Getters and setters (omitted for brevity)

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getPhoto5() {
        return photo5;
    }

    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVinReport() {
        return vinReport;
    }

    public void setVinReport(String vinReport) {
        this.vinReport = vinReport;
    }
}
