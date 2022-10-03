package org.example;
import java.util.Date;

public class ParkingTicket {
    private int slotNumber;
    private String vehicleNumber;
    private Date date;
    private VehicleType vehicleType;
    private ParkingCharge chargeStrategy;

    private final ParkingSlotType slotType;



    public ParkingTicket(int slotNumber, String vehicleNumber, VehicleType vehicleType, ParkingSlotType slotType, Date date) {
        super();
        this.slotNumber = slotNumber;
        this.vehicleNumber = vehicleNumber;
        this.slotType = slotType;
        this.setVehicleType(vehicleType);
        if(this.slotType==ParkingSlotType.CARSLOT){
            this.chargeStrategy = new CarParkingCharge();
        } else if (this.slotType == ParkingSlotType.MOTORBIKESLOT) {
            this.chargeStrategy = new MotorBikeParkingCharge();
        }
        this.date = date;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingCharge getChargeStrategy() {
        return chargeStrategy;
    }

    public ParkingSlotType getSlotType() {
        return slotType;
    }

    @Override
    public String toString() {
        return "Ticket [slotNumber=" + slotNumber + ", vehicleNumber=" + vehicleNumber + ", date=" + date
                + ", vehicleType=" + vehicleType + "]";
    }

}