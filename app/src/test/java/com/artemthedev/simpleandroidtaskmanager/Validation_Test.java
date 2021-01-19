package com.artemthedev.simpleandroidtaskmanager;

import com.artemthedev.simpleandroidtaskmanager.DB.NotesDBHelper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Validation_Test {
    @Test
    public void validate_spaces() {
        assertEquals("dasdas asdasd d s", NotesDBHelper.validateSpaces("dasdas asdasd d s"));
        assertEquals("dasdas asdasd d s", NotesDBHelper.validateSpaces(" dasdas  asdasd d s"));
        assertEquals("dasdas asdas d d s", NotesDBHelper.validateSpaces(" dasdas    asdas    d d s"));
    }

    @Test
    public void validate_enters() {
        assertEquals("dasdas\n asdasd d s", NotesDBHelper.validateEnters("dasdas\n\n asdasd d s"));
        assertEquals("sdasd \n dfsdf \n ggg", NotesDBHelper.validateEnters("sdasd \n\n\n dfsdf \n\n\n ggg\n\n"));
    }

    @Test
    public void validate() {
        assertEquals("Hello, I'm Developer!\n:)", NotesDBHelper.validate("Hello,    I'm    Developer!\n\n\n\n\n:)\n"));
    }
}