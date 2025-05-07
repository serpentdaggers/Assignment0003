import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class StaffGUI extends JFrame implements ActionListener {

    // Declare fields and buttons
    private JTextField vacancyField, designationField, jobTypeField, jobDateField,
    staffNameField, appointedByField, qualificationField, salaryField,
    weeklyHoursField, workingHoursField, wagesField, shiftsField, displayNumberField;

    private JCheckBox joinedCheckBox;

    private final ArrayList<StaffHire> listStaff = new ArrayList<StaffHire>();

    private JButton addFullTimeBtn, addPartTimeBtn, addSalaryBtn, addShiftsBtn,
    terminateBtn, displayNumberBtn, clearBtn;

    public StaffGUI() {
        setTitle("Staff Management GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(8, 4, 5, 5));

        // Initialize form fields
        vacancyField = new JTextField();
        designationField = new JTextField();
        jobTypeField = new JTextField();
        jobDateField = new JTextField();
        staffNameField = new JTextField();
        appointedByField = new JTextField();
        qualificationField = new JTextField();
        salaryField = new JTextField();
        joinedCheckBox = new JCheckBox();
        weeklyHoursField = new JTextField();
        workingHoursField = new JTextField();
        wagesField = new JTextField();
        shiftsField = new JTextField();
        displayNumberField = new JTextField();

        // Add fields to form panel
        formPanel.add(new JLabel("Vacancy:"));
        formPanel.add(vacancyField);
        formPanel.add(new JLabel("Designation:"));
        formPanel.add(designationField);

        formPanel.add(new JLabel("Job Type:"));
        formPanel.add(jobTypeField);
        formPanel.add(new JLabel("Job Date:"));
        formPanel.add(jobDateField);

        formPanel.add(new JLabel("Staff Name:"));
        formPanel.add(staffNameField);
        formPanel.add(new JLabel("Appointed By:"));
        formPanel.add(appointedByField);

        formPanel.add(new JLabel("Qualification:"));
        formPanel.add(qualificationField);
        formPanel.add(new JLabel("Salary:"));
        formPanel.add(salaryField);

        formPanel.add(new JLabel("Joined:"));
        formPanel.add(joinedCheckBox);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        formPanel.add(new JLabel("Weekly Fractional Hours:"));
        formPanel.add(weeklyHoursField);
        formPanel.add(new JLabel("Working Hours:"));
        formPanel.add(workingHoursField);

        formPanel.add(new JLabel("Wages Per Hour:"));
        formPanel.add(wagesField);
        formPanel.add(new JLabel("Shifts:"));
        formPanel.add(shiftsField);

        formPanel.add(new JLabel("Display Number:"));
        formPanel.add(displayNumberField);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));

        // Initialize buttons
        addFullTimeBtn = new JButton("Add Fulltime Staff");
        addPartTimeBtn = new JButton("Add Parttime Staff");
        addSalaryBtn = new JButton("Add Salary");
        addShiftsBtn = new JButton("Add Working Shifts");
        terminateBtn = new JButton("Terminate Staff");
        displayNumberBtn = new JButton("Display Number");
        clearBtn = new JButton("Clear");

        // Add buttons to panel
        buttonPanel.add(addFullTimeBtn);
        buttonPanel.add(addPartTimeBtn);
        buttonPanel.add(addSalaryBtn);
        buttonPanel.add(addShiftsBtn);
        buttonPanel.add(terminateBtn);
        buttonPanel.add(displayNumberBtn);
        buttonPanel.add(clearBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add listeners
        addListeners();

        setVisible(true);
    }

    // Method to add action listeners
    private void addListeners() {
        addFullTimeBtn.addActionListener(this);
        addPartTimeBtn.addActionListener(this);
        addSalaryBtn.addActionListener(this);
        addShiftsBtn.addActionListener(this);
        terminateBtn.addActionListener(this);
        displayNumberBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    public void setSalary() {
        String vacancyText = vacancyField.getText().trim();
        String enteredName = staffNameField.getText().trim();
        String salaryText = salaryField.getText().trim();

        if (vacancyText.isEmpty() || enteredName.isEmpty() || salaryText.isEmpty()) {
            showErrorDialog("Please enter a vacancy number, name, and salary.");
            return;
        }

        int vacancyNumber = validateVacancyNumber(vacancyText);
        if (vacancyNumber == -1) return;

        double salaryAmount = validateSalary(salaryText);
        if (salaryAmount == -1) return;

        StaffHire matchedStaff = findStaffByVacancyAndName(vacancyNumber, enteredName);
        if (matchedStaff == null) {
            showErrorDialog("Full-time staff with vacancy number '" + vacancyText +
                "' and name '" + enteredName + "' not found.");
            return;
        }

        if (matchedStaff instanceof FullTimeStaffHire) {
            FullTimeStaffHire fullTimeStaff = (FullTimeStaffHire) matchedStaff;
            updateSalary(fullTimeStaff, salaryAmount);
        } else {
            showErrorDialog("The staff is not a full-time staff member. Cannot update salary.");
        }
    }

    private int validateVacancyNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showErrorDialog("Vacancy number must be a valid integer.");
            return -1;
        }
    }

    private double validateSalary(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            showErrorDialog("Salary must be a valid number.");
            return -1; 
        }
    }

    private StaffHire findStaffByVacancyAndName(int vacancyNumber, String staffName) {
        for (StaffHire staff : listStaff) {
            if (staff.getVacancyNumber() == vacancyNumber && staff.getStaffName().equalsIgnoreCase(staffName)) {
                return staff;
            }
        }
        return null;
    }

    private void updateSalary(FullTimeStaffHire staff, double newSalary) {
        staff.setSalary(newSalary);

        String confirmation = String.format(
                "Salary updated for Full-time Staff:\nVacancy Number: %d\nStaff Name: %s\nNew Salary: £%.2f",
                staff.getVacancyNumber(), staff.getStaffName(), staff.getSalary());

        System.out.println("------------------------------");
        System.out.println(confirmation);
        System.out.println("------------------------------");

        JOptionPane.showMessageDialog(this,
            "Salary updated to £" + newSalary + " for vacancy number " + staff.getVacancyNumber() + ".",
            "Salary Updated", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayAllStaff() {
        if (listStaff.isEmpty()) {
            System.out.println("No staff members added yet.");
            return;
        }

        System.out.println("========== All Staff Details ==========");

        for (StaffHire staff : listStaff) {
            System.out.println("------------------------------");
            System.out.println("Vacancy Number: " + staff.getVacancyNumber());
            System.out.println("Designation   : " + staff.getDesignation());
            System.out.println("Job Type      : " + staff.getJobType());
            System.out.println("Staff Name    : " + staff.getStaffName());

            if (staff instanceof FullTimeStaffHire) {
                FullTimeStaffHire fullTime = (FullTimeStaffHire) staff;
                System.out.println("Salary        : £" + fullTime.getSalary());
                System.out.println("Working Hours : " + fullTime.getWeeklyFractionalHours());
                System.out.println("Appointed By  : " + fullTime.getAppointedBy());
                System.out.println("Qualification : " + fullTime.getQualification());
                System.out.println("Joining Date  : " + fullTime.getJoiningDate());
            } else if (staff instanceof PartTimeStaffHire) {
                PartTimeStaffHire partTime = (PartTimeStaffHire) staff;
                System.out.println("Wages per Hr  : £" + partTime.getWagesPerHour());
                System.out.println("Working Hours : " + partTime.getWorkingHour());
                System.out.println("Shift         : " + partTime.getShifts());
                System.out.println("Appointed By  : " + partTime.getAppointedBy());
                System.out.println("Qualification : " + partTime.getQualification());
                System.out.println("Joining Date  : " + partTime.getJoiningDate());
            }
        }

        System.out.println("=======================================");
    }

    public void terminateStaff() {
        String vacancyInput = vacancyField.getText().trim();
        String staffName = staffNameField.getText().trim();

        if (vacancyInput.isEmpty() || staffName.isEmpty()) {
            System.out.println("Enter vacancy number and staff name to terminate.");
            return;
        }

        int vacancy;
        try {
            vacancy = Integer.parseInt(vacancyInput);
        } catch (NumberFormatException e) {
            System.out.println("Vacancy number must be a valid number.");
            return;
        }

        for (StaffHire staff : listStaff) {
            if (staff.getVacancyNumber() == vacancy && staff.getStaffName().equalsIgnoreCase(staffName)) {
                if (staff instanceof PartTimeStaffHire) {
                    PartTimeStaffHire partTime = (PartTimeStaffHire) staff;
                    partTime.terminateStaff();
                    System.out.println("Staff member terminated: " + staffName);
                    return;
                } else if (staff instanceof FullTimeStaffHire) {
                    System.out.println("Termination logic for full-time staff not implemented.");
                    return;
                }
            }
        }

        System.out.println("Staff not found with the given details.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == addFullTimeBtn) {
            // handle full-time staff addition
            System.out.println("Add Full-time staff logic");
        } else if (src == addPartTimeBtn) {
            // handle part-time staff addition
            System.out.println("Add Part-time staff logic");
        } else if (src == addSalaryBtn) {
            // handle salary logic
            System.out.println("Add salary logic");
        } else if (src == addShiftsBtn) {
            // handle working shifts logic
            System.out.println("Add shifts logic");
        } else if (src == terminateBtn) {
            terminateStaff();
        } else if (src == displayNumberBtn) {
            // handle display number logic
            System.out.println("Display Number logic");
        } else if (src == clearBtn) {
            clearInputFields();
        }
    }

    public void clearInputFields() {
        vacancyField.setText("");
        designationField.setText("");
        jobTypeField.setText("");
        jobDateField.setText("");
        staffNameField.setText("");
        appointedByField.setText("");
        qualificationField.setText("");
        salaryField.setText("");
        joinedCheckBox.setSelected(false);
        weeklyHoursField.setText("");
        workingHoursField.setText("");
        wagesField.setText("");
        shiftsField.setText("");
        displayNumberField.setText("");

        System.out.println("Input fields have been cleared.");
    }

    public static void main(String[] args) {
        new StaffGUI();
    }
}