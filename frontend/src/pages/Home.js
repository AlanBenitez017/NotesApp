import React, { useEffect, useState } from "react";
import { getAllNotes, deleteNote, updateNote, createNote, filterByArchived } from "../services/noteService";

function Home() {
  const [notes, setNotes] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [formData, setFormData] = useState({ id: null, title: "", content: "", archived: false });

  useEffect(() => {
    fetchNotes();
  }, []);

  const fetchNotes = async () => {
    const data = await getAllNotes();
    setNotes(data);
  };

  const fetchNotesFilteredByArchived = async (archived) => {
    const data = await filterByArchived(archived);
    setNotes(data);
  };

  const handleDelete = async (id) => {
    await deleteNote(id);
    fetchNotes();
  };

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditing) {
      await updateNote(formData.id, formData);
    } else {
      await createNote(formData);
    }
    resetForm();
    fetchNotes();
  };

  const handleEdit = (note) => {
    setFormData({ id: note.id, title: note.title, content: note.content, archived: note.archived });
    setIsEditing(true);
    setShowForm(true);
  };

  const resetForm = () => {
    setFormData({ id: null, title: "", content: "", archived: false });
    setIsEditing(false);
    setShowForm(false);
  };

  return (
    <div>
      <h1 style={{marginLeft: '15px'}}>Notes</h1>
      <button onClick={() => { setShowForm(true); setIsEditing(false); }}>Add Note</button>
      <button onClick={fetchNotes}>See All Notes</button>
      <button onClick={() => fetchNotesFilteredByArchived(true)}>See Archived Notes</button>
      <button onClick={() => fetchNotesFilteredByArchived(false)}>See Unarchived Notes</button>
      
      {showForm && (
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="title"
            placeholder="Title"
            value={formData.title}
            onChange={handleInputChange}
            required
          />
          <textarea
            name="content"
            placeholder="Content"
            value={formData.content}
            onChange={handleInputChange}
            required
          />
          <label>
            <input
              type="checkbox"
              name="archived"
              checked={formData.archived}
              onChange={handleInputChange}
            />
            Archive
          </label>
          <button type="submit">{isEditing ? "Update Note" : "Add Note"}</button>
          <button type="button" onClick={resetForm}>Cancel</button>
        </form>
      )}

      <ul>
        {notes.map((note) => (
          <li key={note.id} className="note">
            <h3>{note.title}</h3>
            <p>{note.content}</p>
            <button onClick={() => handleEdit(note)}>Edit</button>
            <button onClick={() => handleDelete(note.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Home;
