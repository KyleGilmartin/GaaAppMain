package edu.itsligo.gaaappmain.Lotto;

public class HelperClass {
    String nameofPerson;
    String codeOfPerson;

    public HelperClass(String nameofPerson, String codeOfPerson) {
        this.nameofPerson = nameofPerson;
        this.codeOfPerson = codeOfPerson;
    }

    public String getNameofPerson() {
        return nameofPerson;
    }

    public void setNameofPerson(String nameofPerson) {
        this.nameofPerson = nameofPerson;
    }

    public String getCodeOfPerson() {
        return codeOfPerson;
    }

    public void setCodeOfPerson(String codeOfPerson) {
        this.codeOfPerson = codeOfPerson;
    }
}
