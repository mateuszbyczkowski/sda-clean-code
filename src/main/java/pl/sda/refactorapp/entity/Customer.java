package pl.sda.refactorapp.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import pl.sda.refactorapp.annotation.Entity;
import pl.sda.refactorapp.annotation.Id;

/**
 * The customer, can be person or company
 */
@Entity
public class Customer {

    // customer types
    public static final int COMPANY = 1;
    public static final int PERSON = 2;

    @Id
    private UUID id;

    private int type;
    private LocalDateTime ctime;
    private String email;
    private LocalDateTime verfTime;
    private boolean verf;
    private CustomerVerifier verifBy;

    // company data
    private String compName;
    private String compVat;

    // person data
    private String fName;
    private String lName;
    private String pesel;

    // address data
    private String addrStreet;
    private String addrCity;
    private String addrZipCode;
    private String addrCountryCode;

    private String password;

    private CardData cardData;

    public CardData getCardData() {
        return cardData;
    }

    public void setCardData(CardData cardData) {
        this.cardData = cardData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PreferredPayment getPreferredPayment() {
        return preferredPayment;
    }

    public void setPreferredPayment(PreferredPayment preferredPayment) {
        this.preferredPayment = preferredPayment;
    }

    private PreferredPayment preferredPayment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompVat() {
        return compVat;
    }

    public void setCompVat(String compVat) {
        this.compVat = compVat;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrZipCode() {
        return addrZipCode;
    }

    public void setAddrZipCode(String addrZipCode) {
        this.addrZipCode = addrZipCode;
    }

    public String getAddrCountryCode() {
        return addrCountryCode;
    }

    public void setAddrCountryCode(String addrCountryCode) {
        this.addrCountryCode = addrCountryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getVerfTime() {
        return verfTime;
    }

    public void setVerfTime(LocalDateTime verfTime) {
        this.verfTime = verfTime;
    }

    public boolean isVerf() {
        return verf;
    }

    public void setVerf(boolean verf) {
        this.verf = verf;
    }

    public CustomerVerifier getVerifBy() {
        return verifBy;
    }

    public void setVerifBy(CustomerVerifier verifBy) {
        this.verifBy = verifBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return type == customer.type && verf == customer.verf && Objects.equals(id, customer.id)
            && Objects.equals(ctime, customer.ctime) && Objects.equals(email, customer.email)
            && Objects.equals(verfTime, customer.verfTime) && verifBy == customer.verifBy && Objects
            .equals(compName, customer.compName) && Objects.equals(compVat, customer.compVat) && Objects
            .equals(fName, customer.fName) && Objects.equals(lName, customer.lName) && Objects
            .equals(pesel, customer.pesel) && Objects.equals(addrStreet, customer.addrStreet) && Objects
            .equals(addrCity, customer.addrCity) && Objects.equals(addrZipCode, customer.addrZipCode)
            && Objects.equals(addrCountryCode, customer.addrCountryCode);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(id, type, ctime, email, verfTime, verf, verifBy, compName, compVat, fName, lName, pesel, addrStreet,
                addrCity, addrZipCode, addrCountryCode);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", type=" + type +
            ", ctime=" + ctime +
            ", email='" + email + '\'' +
            ", verfTime=" + verfTime +
            ", verf=" + verf +
            ", verifBy=" + verifBy +
            ", compName='" + compName + '\'' +
            ", compVat='" + compVat + '\'' +
            ", fName='" + fName + '\'' +
            ", lName='" + lName + '\'' +
            ", pesel='" + pesel + '\'' +
            ", addrStreet='" + addrStreet + '\'' +
            ", addrCity='" + addrCity + '\'' +
            ", addrZipCode='" + addrZipCode + '\'' +
            ", addrCountryCode='" + addrCountryCode + '\'' +
            '}';
    }
}