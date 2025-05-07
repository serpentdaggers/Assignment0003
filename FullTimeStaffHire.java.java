public class FullTimeStaffHire extends StaffHire {
    private double salary;
    private int weeklyFractionalHours;

    public FullTimeStaffHire(int vacancyNumber, String designation, String jobType, String staffName, String joiningDate, String qualification, String appointedBy, boolean joined, double salary, int weeklyFractionalHours) {
        super(vacancyNumber, designation, jobType, staffName, joiningDate, qualification, appointedBy, joined);
        this.salary = salary;
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    // Getters and Setters
    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if (isJoined()) {
            this.salary = salary;
            System.out.println("Salary updated to " + salary);
        } else {
            System.out.println("No staff appointed to set salary.");
        }
    }

    public int getWeeklyFractionalHours() { return weeklyFractionalHours; }
    public void setWeeklyFractionalHours(int weeklyFractionalHours) {
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    // Display Method
    @Override
    public void display() {
        super.display();
        System.out.println("Salary: " + salary);
        System.out.println("Weekly Fractional Hours: " + weeklyFractionalHours);
    }
}
