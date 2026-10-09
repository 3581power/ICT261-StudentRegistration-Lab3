package lab2.Screenshot;

public class Student {
    private final String name;
    private final String studentNumber;
    private final String year;
    private final String province;
    private final String programme;
    private final String gender;
    private final String email;
    private final String phone;
    private final String dob;

    public Student(String name, String studentNumber, String year,
                   String province, String programme, String gender,
                   String email, String phone, String dob) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.year = year;
        this.province = province;
        this.programme = programme;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public String getName() { return name; }
    public String getStudentNumber() { return studentNumber; }
    public String getYear() { return year; }
    public String getProvince() { return province; }
    public String getProgramme() { return programme; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDob() { return dob; }
}