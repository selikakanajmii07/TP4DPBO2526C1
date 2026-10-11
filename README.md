# TP4DPBO2526C1
JANJI
Saya Selika Kanajmi dengan NIM 2408495 mengerjakan TP 4 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan

PENJELASAN
Komponen form
- JTextField untuk ID, Nama, dan Tahun Lahir.
- JComboBox untuk Kategori (Pasien, Dokter, Perawat, Apoteker, Staf Administrasi).
- JRadioButton Pria dan Wanita dalam satu ButtonGroup, jadi hanya bisa pilih salah satu.
- Tombol Add/Update, Cancel, dan Delete, serta JTable untuk menampilkan data.

Fungsi di PeopleMenu
- populateList, mengisi 10 data awal.
- setTable, membuat isi tabel dari listPerson.
- insertData, menambah data baru.
- updateData, mengubah data pada baris yang dipilih.
- deleteData, menghapus data.
- clearForm, mengosongkan form dan mengembalikan tombol ke mode Add.
- validasiForm, memeriksa input sebelum disimpan. ID dan nama tidak boleh kosong, tahun lahir harus angka 1900 sampai tahun sekarang, kategori dan jenis kelamin wajib dipilih, dan ID tidak boleh sama dengan data lain.

Konfirmasi hapus, sebelum data dihapus muncul pop-up konfirmasi. Data hanya terhapus kalau user memilih Yes.

ALUR
1. main membuat objek PeopleMenu lalu menampilkan window.
2. Constructor mengisi 10 data awal lewat populateList, lalu tabel menampilkan semua data.
3. Untuk Create, isi form lalu klik Add. Data divalidasi ditambahkan ke listPerson tabel diperbarui dan form dikosongkan.
4. Untuk Update, klik satu baris tabel. Form terisi otomatis, tombol Add berubah jadi Update dan tombol Delete muncul ubah data lalu klik Update maka data di listPerson dan tabel ikut berubah.
5. Untuk Delete, klik satu baris lalu klik Delete setelah user memilih Yes di pop-up konfirmasi data dihapus dari listPerson dan tabel diperbarui.
6. Tombol Cancel membatalkan pilihan baris dan mengosongkan form.

DOKUMENTASI
Dokumentasi ada di folder Dokumentasi berupa screen record
