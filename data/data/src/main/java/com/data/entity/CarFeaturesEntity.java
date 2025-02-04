package com.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "car_features")
public class CarFeaturesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference // Prevent infinite recursion
    private CarEntity car;

    private boolean ACFront;
    private boolean ACRear;
    private boolean backupCamera;
    private boolean cruiseControl;
    private boolean navigation;
    private boolean powerLocks;
    private boolean sunroof;
    private boolean heatedSeats;
    private boolean bluetooth;
    private boolean amFmStereo;
    private boolean cdPlayer;
    private boolean dvdSystem;
    private boolean mp3Player;
    private boolean portableAudio;
    private boolean premiumAudio;
    private boolean airbagDriver;
    private boolean airbagPassenger;
    private boolean antiLockBrakes;
    private boolean handsFree;
    private boolean fogLights;
    private boolean powerWindows;
    private boolean windowsDefroster;
    private boolean rearWindow;
    private boolean wiperTintedGlass;
    private boolean towPackage;
    private boolean bucketSeats;
    private boolean leatherInterior;
    private boolean memorySeats;
    private boolean powerSeats;
    private boolean thirdRowSeats;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public boolean isACFront() {
        return ACFront;
    }

    public void setACFront(boolean ACFront) {
        this.ACFront = ACFront;
    }

    public boolean isACRear() {
        return ACRear;
    }

    public void setACRear(boolean ACRear) {
        this.ACRear = ACRear;
    }

    public boolean isBackupCamera() {
        return backupCamera;
    }

    public void setBackupCamera(boolean backupCamera) {
        this.backupCamera = backupCamera;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public boolean isNavigation() {
        return navigation;
    }

    public void setNavigation(boolean navigation) {
        this.navigation = navigation;
    }

    public boolean isPowerLocks() {
        return powerLocks;
    }

    public void setPowerLocks(boolean powerLocks) {
        this.powerLocks = powerLocks;
    }

    public boolean isSunroof() {
        return sunroof;
    }

    public void setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
    }

    public boolean isHeatedSeats() {
        return heatedSeats;
    }

    public void setHeatedSeats(boolean heatedSeats) {
        this.heatedSeats = heatedSeats;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public boolean isAmFmStereo() {
        return amFmStereo;
    }

    public void setAmFmStereo(boolean amFmStereo) {
        this.amFmStereo = amFmStereo;
    }

    public boolean isCdPlayer() {
        return cdPlayer;
    }

    public void setCdPlayer(boolean cdPlayer) {
        this.cdPlayer = cdPlayer;
    }

    public boolean isDvdSystem() {
        return dvdSystem;
    }

    public void setDvdSystem(boolean dvdSystem) {
        this.dvdSystem = dvdSystem;
    }

    public boolean isMp3Player() {
        return mp3Player;
    }

    public void setMp3Player(boolean mp3Player) {
        this.mp3Player = mp3Player;
    }

    public boolean isPortableAudio() {
        return portableAudio;
    }

    public void setPortableAudio(boolean portableAudio) {
        this.portableAudio = portableAudio;
    }

    public boolean isPremiumAudio() {
        return premiumAudio;
    }

    public void setPremiumAudio(boolean premiumAudio) {
        this.premiumAudio = premiumAudio;
    }

    public boolean isAirbagDriver() {
        return airbagDriver;
    }

    public void setAirbagDriver(boolean airbagDriver) {
        this.airbagDriver = airbagDriver;
    }

    public boolean isAirbagPassenger() {
        return airbagPassenger;
    }

    public void setAirbagPassenger(boolean airbagPassenger) {
        this.airbagPassenger = airbagPassenger;
    }

    public boolean isAntiLockBrakes() {
        return antiLockBrakes;
    }

    public void setAntiLockBrakes(boolean antiLockBrakes) {
        this.antiLockBrakes = antiLockBrakes;
    }

    public boolean isHandsFree() {
        return handsFree;
    }

    public void setHandsFree(boolean handsFree) {
        this.handsFree = handsFree;
    }

    public boolean isFogLights() {
        return fogLights;
    }

    public void setFogLights(boolean fogLights) {
        this.fogLights = fogLights;
    }

    public boolean isPowerWindows() {
        return powerWindows;
    }

    public void setPowerWindows(boolean powerWindows) {
        this.powerWindows = powerWindows;
    }

    public boolean isWindowsDefroster() {
        return windowsDefroster;
    }

    public void setWindowsDefroster(boolean windowsDefroster) {
        this.windowsDefroster = windowsDefroster;
    }

    public boolean isRearWindow() {
        return rearWindow;
    }

    public void setRearWindow(boolean rearWindow) {
        this.rearWindow = rearWindow;
    }

    public boolean isWiperTintedGlass() {
        return wiperTintedGlass;
    }

    public void setWiperTintedGlass(boolean wiperTintedGlass) {
        this.wiperTintedGlass = wiperTintedGlass;
    }

    public boolean isTowPackage() {
        return towPackage;
    }

    public void setTowPackage(boolean towPackage) {
        this.towPackage = towPackage;
    }

    public boolean isBucketSeats() {
        return bucketSeats;
    }

    public void setBucketSeats(boolean bucketSeats) {
        this.bucketSeats = bucketSeats;
    }

    public boolean isLeatherInterior() {
        return leatherInterior;
    }

    public void setLeatherInterior(boolean leatherInterior) {
        this.leatherInterior = leatherInterior;
    }

    public boolean isMemorySeats() {
        return memorySeats;
    }

    public void setMemorySeats(boolean memorySeats) {
        this.memorySeats = memorySeats;
    }

    public boolean isPowerSeats() {
        return powerSeats;
    }

    public void setPowerSeats(boolean powerSeats) {
        this.powerSeats = powerSeats;
    }

    public boolean isThirdRowSeats() {
        return thirdRowSeats;
    }

    public void setThirdRowSeats(boolean thirdRowSeats) {
        this.thirdRowSeats = thirdRowSeats;
    }
}

