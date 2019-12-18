package bBlackjackFunctional

import org.junit.Assert.assertEquals
import org.junit.Test

class GameTest {

    @Test
    fun test() {
        Game(shuffle = false).apply {
            assertEquals(52, deck.size)
            assertEquals(0, ph.size)
            assertEquals(0, dh.size)
            check(state is NotStarted)
        }.deal().apply {
            //ph: ace, 2
            //dh: 3, 4
            assertEquals(48, deck.size)
            assertEquals(2, ph.size)
            assertEquals(2, dh.size)
            check(state is Active)
        }.hit().apply {
            //ph: ace, 2, 5
            //dh: 3, 4
            assertEquals(47, deck.size)
            assertEquals(3, ph.size)
            assertEquals(2, dh.size)
            check(state is Active)
        }.hit().apply {
            //ph: ace, 2, 5, 6
            //dh: 3, 4
            assertEquals(46, deck.size)
            assertEquals(4, ph.size)
            assertEquals(2, dh.size)
            check(state is Active)
        }.hit().apply {
            //ph: ace, 2, 5, 6, 7
            assertEquals(45, deck.size)
            assertEquals(5, ph.size)
            assertEquals(2, dh.size)
            check(state is Active)
        }.deal().apply {
            //ph:  8 9
            //dh:  10 J
            assertEquals(41, deck.size)
            assertEquals(2, ph.size)
            assertEquals(17, ph.points)
            assertEquals(2, dh.size)
            assertEquals(20, dh.points)
            check(state is Active)
        }.stay().apply {
            //sets ph.stay to true and auto-hits dealer as londh.points < 17
            //ph:  8 9
            //dh:  10 J
            assertEquals(41, deck.size)
            assertEquals(2, ph.size)
            assertEquals(17, ph.points)
            check(ph.isStay)
            assertEquals(2, dh.size)
            assertEquals(20, dh.points)
            check(state is Over)
        }


    }

    @Test
    fun testManyDeals() {
        var g = Game()
        repeat(100) {
            g = g.deal()
            check(g.isDealClean)
            check(g.state is Active)
            g = g.hit()
            check(!g.isDealClean)
            g = g.stay()
            check(g.state is Over)
        }
    }
}


