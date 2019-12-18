package dExamplets.aConstructors

import java.sql.ResultSet

class Foo() {

    companion object {
        fun from(rs: ResultSet): Foo {
            return Foo()
        }

        fun of(rs: ResultSet): Foo {
            return Foo()
        }
    }

}

