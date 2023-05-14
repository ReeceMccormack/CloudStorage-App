package com.udacity.jwdnd.course1.cloudstorage.Repo;


import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM notes WHERE userid = #{userId}")
    List<Note> getNotesByUserId(int userId);

    @Insert("INSERT INTO notes (notetitle,  notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNotes(Note notes);

    @Delete("DELETE FROM notes WHERE noteid = #{noteId}")
    void deleteNote(int noteId);

    @Select("SELECT * FROM notes WHERE noteid = #{noteId}")
    Note getNoteById(int noteId);

    @Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    public void updateNote(Note notes);
}
