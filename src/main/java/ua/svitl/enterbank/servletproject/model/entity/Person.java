package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person implements Serializable {

    private static final long serialVersionUID = -7069751590165153190L;

    private Integer personId;
    private LocalDateTime birthDate;
    private String firstName;
    private Long idNumberTaxCode;
    private String lastName;
    private String middleName = " ";
    private String secretWord;
    private List<Address> userAddresses = new ArrayList<>();
    private List<PassportData> personPassports = new ArrayList<>();
    private List<BankAccount> personAccounts = new ArrayList<>();
    private List<PhoneNumber> personPhoneNumbers = new ArrayList<>();
    private List<User> personUsers = new ArrayList<>();

    public Person() {
    }

    public Person(Integer personId, LocalDateTime birthDate, String firstName, Long idNumberTaxCode, String lastName,
                  String middleName, String secretWord,
                  List<Address> userAddresses, List<PassportData> personPassports, List<BankAccount> personAccounts,
                  List<PhoneNumber> personPhoneNumbers, List<User> personUsers) {
        this.personId = personId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.idNumberTaxCode = idNumberTaxCode;
        this.lastName = lastName;
        this.middleName = middleName;
        this.secretWord = secretWord;
        this.userAddresses = userAddresses;
        this.personPassports = personPassports;
        this.personAccounts = personAccounts;
        this.personPhoneNumbers = personPhoneNumbers;
        this.personUsers = personUsers;
    }

    public Person(LocalDateTime birthDate, String firstName, Long idNumberTaxCode, String lastName,
                  String middleName, String secretWord,
                  List<Address> userAddresses, List<PassportData> personPassports, List<BankAccount> personAccounts,
                  List<PhoneNumber> personPhoneNumbers, List<User> personUsers) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.idNumberTaxCode = idNumberTaxCode;
        this.lastName = lastName;
        this.middleName = middleName;
        this.secretWord = secretWord;
        this.userAddresses = userAddresses;
        this.personPassports = personPassports;
        this.personAccounts = personAccounts;
        this.personPhoneNumbers = personPhoneNumbers;
        this.personUsers = personUsers;
    }

    public Person(Integer personId, LocalDateTime birthDate, String firstName, Long idNumberTaxCode, String lastName,
                  String middleName, String secretWord,
                  Address userAddress, PassportData personPassport, BankAccount personAccount,
                  PhoneNumber personPhoneNumber, User user) {
        this.personId = personId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.idNumberTaxCode = idNumberTaxCode;
        this.lastName = lastName;
        this.middleName = middleName;
        this.secretWord = secretWord;
        userAddresses.add(userAddress);
        personPassports.add(personPassport);
        personAccounts.add(personAccount);
        personPhoneNumbers.add(personPhoneNumber);
        personUsers.add(user);
    }

    public Person(LocalDateTime birthDate, String firstName, Long idNumberTaxCode, String lastName,
                  String middleName, String secretWord,
                  Address userAddress, PassportData personPassport, BankAccount personAccount,
                  PhoneNumber personPhoneNumber, User user) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.idNumberTaxCode = idNumberTaxCode;
        this.lastName = lastName;
        this.middleName = middleName;
        this.secretWord = secretWord;
        userAddresses.add(userAddress);
        personPassports.add(personPassport);
        personAccounts.add(personAccount);
        personPhoneNumbers.add(personPhoneNumber);
        personUsers.add(user);
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setIdNumberTaxCode(Long idNumberTaxCode) {
        this.idNumberTaxCode = idNumberTaxCode;
    }

    public Long getIdNumberTaxCode() {
        return idNumberTaxCode;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public List<Address> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public void setUserAddress(Address userAddress) {
        userAddresses.add(userAddress);
    }

    public List<PassportData> getPersonPassports() {
        return personPassports;
    }

    public void setPersonPassports(List<PassportData> personPassports) {
        this.personPassports = personPassports;
    }

    public void setPersonPassport(PassportData personPassport) {
        personPassports.add(personPassport);
    }

    public List<BankAccount> getPersonAccounts() {
        return personAccounts;
    }

    public void setPersonAccounts(List<BankAccount> personAccounts) {
        this.personAccounts = personAccounts;
    }

    public void setPersonAccount(BankAccount personAccount) {
        personAccounts.add(personAccount);
    }

    public List<PhoneNumber> getPersonPhoneNumbers() {
        return personPhoneNumbers;
    }

    public void setPersonPhoneNumbers(List<PhoneNumber> personPhoneNumbers) {
        this.personPhoneNumbers = personPhoneNumbers;
    }

    public void setPersonPhoneNumber(PhoneNumber personPhoneNumber) {
        personPhoneNumbers.add(personPhoneNumber);
    }

    public List<User> getPersonUsers() {
        return personUsers;
    }

    public void setPersonUsers(List<User> personUsers) {
        this.personUsers = personUsers;
    }

    public void setPersonUser(User user) {
        personUsers.add(user);
    }

    @Override
    public String toString() {
        return "PersonData{" +
                "personId=" + personId + '\'' +
                "idNumberTaxCode=" + idNumberTaxCode + '\'' +
                "lastName=" + lastName + '\'' +
                "firstName=" + firstName + '\'' +
                "middleName=" + middleName + '\'' +
                "birthDate=" + birthDate + '\'' +
                "username=" + Arrays.toString(personUsers.toArray()) + '\'' +
                '}';
    }
    /**
     * The Person ID is unique for each Person. So this should compare Person by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Person) && (personId != null)
                ? personId.equals(((Person) other).personId)
                : (other == this);
    }

    /**
     * The person ID is unique for each Person. So Person with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (personId != null)
                ? (this.getClass().hashCode() + personId.hashCode())
                : super.hashCode();
    }
}

