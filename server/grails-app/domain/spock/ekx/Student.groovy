package spock.ekx

class Student {

    String name
    String email

    static constraints = {
        name blank: false, maxSize: 25
        email nullable: true, email: true, unique: true
    }
}
