🎵 Welcome to the Java Music Platform (V01Dify)— a terminal-based application inspired by Genius.
Built with Java, this platform allows users to interact with songs, artists, comments, 
likes, and a full Q&A system — all stored using file-based architecture.

🚀 Features :

👑 Admin

Approves artist applications.
Reviews and applies lyric edit requests.

🎤 Artist

Uploads songs and creates albums.
Manages their own song lyrics.
Views and responds to edit requests.

👤 User

Likes / Dislikes songs.
Adds comments to songs.
Asks & answers questions on songs.
Follows artists and views their following list.
Uses search and random song suggestions.

📁 File-Based Architecture

All data is stored in .txt files in the data/ directory:

bash
Copy
Edit
data/
├── songs/              # Each song has a dedicated .txt file
├── likes/              # Stores user likes/dislikes per song
├── comments/           # (Optional if separated)
├── qna.txt             # Q&A questions/answers
├── users.txt           # Registered users
├── artists.txt         # Registered artists
├── admin.txt           # Admin credentials


⚙️ How It Works

1. 🔐 Login System
   Based on credentials, user is routed to:

AdminPanel
ArtistPanel
UserPanel

2. 📀 Song Management
   Each song file contains:

Title
Artist
Genre
Lyrics
Like/Dislike count
Comments
Q&A section

3. 🧠 User Interaction

Likes/dislikes are stored directly in each song file.
Comments are appended in the same file.
Q&A is integrated inside the song's text content.

4. 🔍 Search & Discover
   Users can:

Search by title/genre.
Get a random song recommendation.

💡 Bonus Features

✅ Fully file-based (no database).
✅ Song details viewer.
✅ Artists can edit lyrics upon approval.
✅ Admin-driven content moderation.
✅ Supports Q&A per song.
✅ Like and Dislike option.
✅ More advanced search system
✅ comment section added in order to make a better felling in UX.

🖥️ How to Run

Open the project in IntelliJ IDEA or any Java IDE.

Ensure this structure exists:

pgsql
Copy
Edit
data/
├── songs/
├── likes/
├── qna.txt
└── other user/admin files

Run the Main.java or LoginPanel.java to start.

Follow the menu prompts based on your role.

📦 Technologies Used

Java (Console-based)
Object-Oriented Programming
File I/O
Text-based data storage
IntelliJ IDEA

📈 Future Ideas

GUI version using JavaFX or Swing.
Export to database format. 
Enhanced artist profile pages.
Playlist support.

🧠 Author
This project was built as a terminal-based music system for educational 
and portfolio purposes which was inspired by dedicated colleges at SBU

📝 License
This project is open-source and free to use for educational purposes.

