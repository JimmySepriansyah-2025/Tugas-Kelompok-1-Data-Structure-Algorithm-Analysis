Sistem Manajemen Playlist Musik (Java OOP)
Mata Kuliah
COSC6025 – Data Structures and Algorithm Analysis
BINUS Online Learning

Deskripsi Program
Program ini merupakan implementasi Sistem Manajemen Playlist Musik menggunakan bahasa pemrograman Java dengan menerapkan konsep Object-Oriented Programming (OOP) dan struktur data array.
Program ini dibuat untuk memenuhi tugas kelompok pada mata kuliah Data Structures and Algorithm Analysis.
Konsep OOP yang digunakan dalam program ini meliputi:
•	Encapsulation
Atribut pada class Lagu dibuat private dan diakses menggunakan getter dan setter.
•	Inheritance
Class Admin dan Member merupakan turunan dari class User.
•	Polymorphism
Method tampilkanAkses() di-override pada class Admin dan Member.
Program juga menggunakan array objek Lagu untuk menyimpan daftar playlist musik.

Fitur Program
Program memiliki beberapa fitur utama:
1. Admin Playlist
Admin memiliki hak akses untuk:
•	Menambahkan lagu ke dalam playlist
•	Melihat seluruh daftar lagu
2. Member Playlist
Member dapat:
•	Melihat daftar lagu
•	Mencari lagu berdasarkan judul
•	Menghitung rata-rata durasi lagu

Struktur Program
Program terdiri dari beberapa class utama:
•	PlaylistOOP
Class utama yang menjalankan program dan mengatur menu sistem.
•	Lagu
Merepresentasikan data lagu dengan atribut:
o	judul
o	artis
o	durasi
•	User
Parent class yang menjadi dasar bagi jenis pengguna sistem.
•	Admin (extends User)
Memiliki akses untuk menambahkan lagu.
•	Member (extends User)
Memiliki akses untuk melihat dan mencari lagu.

Struktur Repository
Repository ini berisi beberapa file utama:
PlaylistOOP.java
    Program utama sistem playlist musik menggunakan Java

Jawaban_R1_TK1.pdf
    Laporan penjelasan implementasi konsep OOP

Screenshot_Hasil_Eksekusi_Program.jpg
    Bukti hasil eksekusi program

Cara Menjalankan Program
1.	Compile program Java
javac PlaylistOOP.java
2.	Jalankan program
java PlaylistOOP
Program akan menampilkan menu sistem playlist musik pada terminal.

Contoh Output Program
Program akan menampilkan menu seperti berikut:
=== SISTEM PLAYLIST MUSIK ===
Pilih peran:
1. Admin Playlist
2. Member Playlist
0. Keluar

Identitas Kelompok
Group 7
1.	Jimmy Sepriansyah - 2902706364
2.	Muhammad Rizki Kurnia Putra - 2401968362
3.	Charys Sonnia Indriyana - 2902728251
4.	Shafa Alifia Salsabila Nurul Ichsan - 2902697233
5.	Anita - 2902734600

Referensi
1.	Oracle Corporation. The Java™ Tutorials – Learning the Java Language.
https://docs.oracle.com/javase/tutorial/
2.	Oracle Corporation. Primitive Data Types (The Java™ Tutorials).
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
3.	BINUS University. Lecture Notes – Introduction to Java Programming.
4.	BINUS University. Lecture Notes – Data Structures and Algorithm Analysis.
