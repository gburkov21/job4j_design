package ru.job4j.tdd;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    @Ignore
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    @Ignore
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Test
    @Ignore
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Test
    @Ignore
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.size() > 0);
    }

    @Test
    @Ignore
    public void whenSessionsIsNull() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertNull(sessions);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void whenRowAndColumnIsBusy() {
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Account firstAccount = new AccountCinema();
        Ticket firstTicket = cinema.buy(firstAccount, 1, 1, date);
        Account secondAccount = new AccountCinema();
        Ticket secondTicket = cinema.buy(secondAccount, 1, 1, date);
    }
}