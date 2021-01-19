package com.artemthedev.simpleandroidtaskmanager;

import com.artemthedev.simpleandroidtaskmanager.DB.NoteDate;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NoteDate_Test {
    @Test
    public void addition_isCorrect() {
        assertEquals("2017-03-23-11-02-00", new NoteDate(2017, 3,23,11,2,0).toString());
    }
}