package org.example;
import java.util.Date;

public class ParkingTicket {
    private int slotNumber;
    private String vehicleNumber;
    private Date date;
    private VehicleType vehicleType;
    private ParkingCharge chargeStrategy;


    public ParkingTicket(int slotNumber, String vehicleNumber, VehicleType vehicleType, Date date) {
        super();
        this.slotNumber = slotNumber;
        this.vehicleNumber = vehicleNumber;
        this.setVehicleType(vehicleType);
        if(this.vehicleType==VehicleType.CAR){
            this.chargeStrategy = new CarParkingCharge();
        } else if (this.vehicleType == vehicleType.MOTORBIKE) {
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

    @Override
    public String toString() {
        return "Ticket [slotNumber=" + slotNumber + ", vehicleNumber=" + vehicleNumber + ", date=" + date
                + ", vehicleType=" + vehicleType + "]";
    }

}