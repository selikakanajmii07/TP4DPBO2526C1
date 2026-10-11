import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.Year;
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox kategoriComboBox;
    private JPanel genderPanel;
    private JRadioButton priaRadioButton;
    private JRadioButton wanitaRadioButton;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable peopleTable;

    // Data
    private ArrayList<Person> listPerson;
    private int selectedIndex = -1;
    private ButtonGroup genderGroup;

    // Main
    public static void main(String[] args) {
        // buat object window
        PeopleMenu menu = new PeopleMenu();

        // atur ukuran window
        menu.setSize(750, 650);

        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar program ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public PeopleMenu() {
        setTitle("PeopleData - Data Rumah Sakit");

        // inisialisasi listPerson
        listPerson = new ArrayList<>();

        // isi listPerson
        populateList();

        // isi tabel
        peopleTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = { "???", "Pasien", "Dokter", "Perawat", "Apoteker", "Staf Administrasi" };
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // radio button Pria/Wanita dijadikan satu group supaya hanya bisa pilih salah satu
        genderGroup = new ButtonGroup();
        genderGroup.add(priaRadioButton);
        genderGroup.add(wanitaRadioButton);

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // saat tombol delete ditekan menampilkan konfirmasi dulu
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    return;
                }
                String nama = listPerson.get(selectedIndex).getNama();
                int pilihan = JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin menghapus data \"" + nama + "\"?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                // data hanya dihapus kalau user memilih "Yes"
                if (pilihan == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        peopleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                int row = peopleTable.rowAtPoint(e.getPoint());
                if (row == -1) {
                    return;
                }
                selectedIndex = row;

                // simpan value textfield, combo box, dan radio button
                String curId = peopleTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = peopleTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curTahun = peopleTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = peopleTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curGender = peopleTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield, combo box, dan radio button
                idField.setText(curId);
                namaField.setText(curNama);
                tahunLahirField.setText(curTahun);
                kategoriComboBox.setSelectedItem(curKategori);
                if (curGender.equals("Pria")) {
                    priaRadioButton.setSelected(true);
                } else {
                    wanitaRadioButton.setSelected(true);
                }

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    // Isi data awal
    private void populateList() {
        listPerson.add(new Person("P001", "Budi Santoso", 1992, "Pasien", "Pria"));
        listPerson.add(new Person("P002", "Siti Aminah", 1997, "Pasien", "Wanita"));
        listPerson.add(new Person("D001", "Rian Pratama", 1985, "Dokter", "Pria"));
        listPerson.add(new Person("D002", "Dewi Lestari", 1988, "Dokter", "Wanita"));
        listPerson.add(new Person("N001", "Ahmad Fauzi", 1995, "Perawat", "Pria"));
        listPerson.add(new Person("N002", "Nadia Putri", 1994, "Perawat", "Wanita"));
        listPerson.add(new Person("A001", "Rizky Ramadhan", 1990, "Apoteker", "Pria"));
        listPerson.add(new Person("S001", "Maya Indah", 1999, "Staf Administrasi", "Wanita"));
        listPerson.add(new Person("P003", "Hendra Wijaya", 1980, "Pasien", "Pria"));
        listPerson.add(new Person("P004", "Clarissa Yuni", 2003, "Pasien", "Wanita"));
    }

    //Membuat model tabel dari listPerson
    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = { "No", "ID", "Nama", "Tahun Lahir", "Kategori", "Jenis Kelamin" };

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // isi tabel dengan listPerson
        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {
                    i + 1,
                    listPerson.get(i).getId(),
                    listPerson.get(i).getNama(),
                    listPerson.get(i).getTahunLahir(),
                    listPerson.get(i).getKategori(),
                    listPerson.get(i).getJenisKelamin()
            };
            tmp.addRow(row);
        }

        return tmp;
    }

    private String validasiForm(int indexDiabaikan) {
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();

        if (id.isEmpty()) {
            return "ID tidak boleh kosong!";
        }
        if (nama.isEmpty()) {
            return "Nama tidak boleh kosong!";
        }

        try {
            int tahun = Integer.parseInt(tahunLahirField.getText().trim());
            int tahunSekarang = Year.now().getValue();
            if (tahun < 1900 || tahun > tahunSekarang) {
                return "Tahun lahir harus antara 1900 dan " + tahunSekarang + "!";
            }
        } catch (NumberFormatException ex) {
            return "Tahun lahir harus berupa angka!";
        }

        if (kategoriComboBox.getSelectedIndex() == 0) {
            return "Silakan pilih kategori!";
        }
        if (!priaRadioButton.isSelected() && !wanitaRadioButton.isSelected()) {
            return "Silakan pilih jenis kelamin!";
        }

        // ID tidak boleh sama dengan data lain
        for (int i = 0; i < listPerson.size(); i++) {
            if (i != indexDiabaikan && listPerson.get(i).getId().equalsIgnoreCase(id)) {
                return "ID \"" + id + "\" sudah dipakai!";
            }
        }
        return null;
    }

    private String getGenderTerpilih() {
        return priaRadioButton.isSelected() ? "Pria" : "Wanita";
    }

    // create
    public void insertData() {
        String error = validasiForm(-1);
        if (error != null) {
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ambil value dari textfield, combobox, dan radio button
        String id = idField.getText().trim();
        String nama = namaField.getText().trim();
        int tahunLahir = Integer.parseInt(tahunLahirField.getText().trim());
        String kategori = kategoriComboBox.getSelectedItem().toString();
        String gender = getGenderTerpilih();

        // tambahkan data ke dalam list
        listPerson.add(new Person(id, nama, tahunLahir, kategori, gender));

        // update tabel
        peopleTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    // update
    public void updateData() {
        String error = validasiForm(selectedIndex);
        if (error != null) {
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ubah data person di list
        Person p = listPerson.get(selectedIndex);
        p.setId(idField.getText().trim());
        p.setNama(namaField.getText().trim());
        p.setTahunLahir(Integer.parseInt(tahunLahirField.getText().trim()));
        p.setKategori(kategoriComboBox.getSelectedItem().toString());
        p.setJenisKelamin(getGenderTerpilih());

        // update tabel
        peopleTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Update berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    }

    // delete
    public void deleteData() {
        // hapus data dari list
        listPerson.remove(selectedIndex);

        // update tabel
        peopleTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        genderGroup.clearSelection();
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}
