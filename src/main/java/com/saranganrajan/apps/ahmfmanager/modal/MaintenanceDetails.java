package com.saranganrajan.apps.ahmfmanager.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDetails {
    public String SystemName;
    public String ScheduledMaintenanceWindowFrom;
    public String ScheduledMaintenanceWindowTo;
    public String getSystemName() {
        return SystemName;
    }

    public void setSystemName(String systemName) {
        this.SystemName = systemName;
    }

    public String getScheduledMaintenanceWindowFrom() {
        return this.ScheduledMaintenanceWindowFrom;
    }

    public void setScheduledMaintenanceWindowFrom(String scheduledMaintenanceWindowFrom) {
        this.ScheduledMaintenanceWindowTo = scheduledMaintenanceWindowFrom;
    }

    public String getScheduledMaintenanceWindowTo() {
        return ScheduledMaintenanceWindowFrom;
    }

    public void setScheduledMaintenanceWindowTo(String scheduledMaintenanceWindowTo) {
        this.ScheduledMaintenanceWindowTo = scheduledMaintenanceWindowTo;
    }

}
