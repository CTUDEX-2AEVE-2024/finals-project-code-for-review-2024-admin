package com.ctu.reservationportal.model;

/**
 * a class to hold and get Admin Informations/Details
 */
public class AdminInfo {
    private String username;
    private int idNumber;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String street;
    private String barangay;
    private String municipality;
    private String city;
    private int ZIPcode;
    private String nationality;
    private String gender;
    private String roleAtSchool;


    public String getPassword() {return password; }

    public void setPassword(String password) { this.password = password; }

    /**
     * @return first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return birthdate
     */
    public String getBirthdate() {
        return birthDate;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() { return street; }

    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return barangay
     */
    public String getBarangay() {
        return barangay;
    }

    /**
     * @param barangay
     */
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    /**
     * @return municipality
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * @param municipality
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return ZIP code
     */
    public int getZIPcode() {
        return ZIPcode;
    }

    /**
     * @param ziPcode
     */
    public void setZIPcode(int ziPcode) {
        this.ZIPcode = ziPcode;
    }

    /**
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return role at school
     */
    public String getRoleAtSchool() {
        return roleAtSchool;
    }

    public void setRoleAtSchool(String roleAtSchool) {
        this.roleAtSchool = roleAtSchool;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void displayAdminInfo() {
    }
}




