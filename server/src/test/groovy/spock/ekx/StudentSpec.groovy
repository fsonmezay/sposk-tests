package spock.ekx

import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class StudentSpec extends Specification implements DomainUnitTest<Student> {

    @Shared
    Student domain = new Student()

    def setup() {

    }

    void 'test name cannot be null'() {
        when:
        domain.name = null

        then:
        !domain.validate(['name'])
        domain.errors['name'].code == 'nullable'
    }

    void 'test name cannot be blank'() {
        when:
        domain.name = ""

        then:
        !domain.validate(['name'])
        domain.errors['name'].code == 'blank'
    }

    @Unroll("when myName: #myName, errorCode: #errorCode, isValid: #isValid")
    void 'test name is valid'() {
        given:
        domain.name = myName

        expect:
        domain.validate(['name']) == isValid

        and:
        if (!isValid) {
            domain.errors['name'].code == errorCode
        } else {
            domain.errors == errorCode
        }

        where:
        myName  | errorCode  | isValid
        null    | 'nullable' | false
        ""      | 'blank'    | false
        "ferdi" | null       | true
    }

    void 'test name maxLength'() {
        when:
        domain.name = "fstest" * 10

        then:
        !domain.validate(['name'])
        domain.errors['name'].code == 'maxSize.exceeded'
    }

    @Unroll("length #size , #myName")
    void 'test name length'() {
        given:
        domain.name = myName

        expect:
        domain.validate(['name']) == isValid
        if (isValid) {
            domain.errors == errorCode
        } else {
            domain.errors['name'].code == errorCode
        }

        where:
        myName         | errorCode          | isValid | size
        "fstest-"      | null               | true    | myName.length()
        "fstest-" * 3  | null               | true    | myName.length()
        "fstest-" * 5  | 'maxSize.exceeded' | false   | myName.length()
        "fstest-" * 10 | 'maxSize.exceeded' | false   | myName.length()
    }


    def cleanup() {

    }

}
