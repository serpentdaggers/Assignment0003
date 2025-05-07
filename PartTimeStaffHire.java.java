public class PartTimeStaffHire extends StaffHire {
    private int workingHour;
    private double wagesPerHour;
    private String shifts;
    private boolean terminated;

    public PartTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, boolean joined, int workingHour, double wagesPerHour, String shifts) {
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);
        this.workingHour = workingHour;
        this.wagesPerHour = wagesPerHour;
        this.shifts = shifts;
        this.terminated = false;
    }

    // Getters and Setters
    public int getWorkingHour() { return workingHour; }
    public void setWorkingHour(int workingHour) { this.workingHour = workingHour; }

    public double getWagesPerHour() { return wagesPerHour; }
    public void setWagesPerHour(double wagesPerHour) { this.wagesPerHour = wagesPerHour; }

    public String getShifts() { return shifts; }
    public void setShifts(String shifts) {
        if (isJoined()) {
            this.shifts = shifts;
            System.out.println("Shift changed to: " + shifts);
        } else {
            System.out.println("Staff not joined yet. Cannot change shift.");
        }
    }

    public boolean isTerminated() { return terminated; }

    public void terminateStaff() {
        if (terminated) {
            System.out.println("Staff already terminated.");
        } else {
            setStaffName("");
            setJoiningDate("");
            setQualification("");
            setAppointedBy("");
            setJoined(false);
            terminated = true;
            System.out.println("Staff terminated successfully.");
        }
    }

    // Display Method
    @Override
    public void display() {
        super.display();
        System.out.println("Working Hour: " + workingHour);
        System.out.println("Wages Per Hour: " + wagesPerHour);
        System.out.println("Shifts: " + shifts);
        System.out.println("Terminated: " + terminated);
        double incomePerDay = wagesPerHour * workingHour;
        System.out.println("Income per Day: " + incomePerDay);
    }
}
