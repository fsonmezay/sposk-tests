package spock.ekx

import spock.lang.Specification

class SimpleTest extends Specification {

    def stack

    def setup() {
        stack = new Stack<String>()
        assert stack.isEmpty()
    }

    def "should return 2 from first element of list"() {
        given:
        List<Integer> list = new ArrayList<>()
        when:
        list.add(2)
        then:
        2 == list.get(0)
    }

    def 'Write an assertion in the then block'() {

        when: 'We create a new list'
        def list = []

        then: 'Should create an empty list'
        list.isEmpty()
        list.size() == 0
    }

    def 'Write an assertion in the expect block'() {

        expect: 'Should return the bigger number'
        Math.max(1, 2) == 2
    }

    def "test stack pop when empty"() {
        when:
        stack.pop()

        then:
        thrown(EmptyStackException)
        stack.empty
    }

    def "HashMap accepts null key"() {
        setup:
        def map = new HashMap()

        when:
        map.put(null, "elem")

        then:
        notThrown(NullPointerException)
    }

    def "computing the maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [5, 3]
        b << [1, 9]
        c << [5, 9]
    }

    def "computing the maximum of two numbers2"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b | c
        5 | 1 | 5
        3 | 9 | 9

    }

    def "List Invocation Calls Test"() {
        given:
        List list = Mock();
        when:

        list.add(5)
        list.add(15)
        list.add(25)

        then:
        3 * list.add(_)
    }
}
