import React from "react";

const NoteCard = ({ title, content, archived }) => {
  return (
    <div className="note-card">
      <h3>{title}</h3>
      <p>{content}</p>
      <p>Status: {archived ? "Archived" : "Active"}</p>
    </div>
  );
};

export default NoteCard;
