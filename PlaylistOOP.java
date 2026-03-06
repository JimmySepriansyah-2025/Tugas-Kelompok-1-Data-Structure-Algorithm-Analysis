import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * PlaylistOOP.java
 * COSC6025 - Data Structures and Algorithm Analysis
 * Sistem Manajemen Playlist Musik (Array + OOP)
 *
 * Cara compile & run:
 * - javac PlaylistOOP.java
 * - java PlaylistOOP
 */

public class PlaylistOOP {

	private static final int MAX_LAGU = 100;

	public static void main(String[] args) {
		tampilkanIdentitasKelompok(); // tampil di bagian atas saat program dieksekusi

		Scanner sc = new Scanner(System.in);

		Lagu[] playlist = new Lagu[MAX_LAGU]; // Array untuk menyimpan objek Lagu
		int jumlahLagu = 0;

		// Data awal contoh (agar tidak kosong)
		playlist[jumlahLagu++] = new Lagu("Fix You", "Coldplay", 4.55);
		playlist[jumlahLagu++] = new Lagu("Bohemian Rhapsody", "Queen", 5.55);

		boolean jalan = true;
		while (jalan) {
			System.out.println("=== SISTEM PLAYLIST MUSIK ===");
			System.out.println("Pilih peran:");
			System.out.println("1. Admin Playlist");
			System.out.println("2. Member Playlist");
			System.out.println("0. Keluar");
			System.out.print("Input: ");

			int pilihPeran = bacaInt(sc);

			switch (pilihPeran) {
			case 1: {
				User admin = new Admin("Admin-01"); // Inheritance (Admin extends User)
				admin.tampilkanAkses();              // Polymorphism (override)
				jumlahLagu = menuAdmin(sc, (Admin) admin, playlist, jumlahLagu);
				break;
			}
			case 2: {
				User member = new Member("Member-01"); // Inheritance (Member extends User)
				member.tampilkanAkses();               // Polymorphism (override)
				menuMember(sc, (Member) member, playlist, jumlahLagu);
				break;
			}
			case 0:
				jalan = false;
				System.out.println("Program selesai. Terima kasih!");
				break;
			default:
				System.out.println("Pilihan tidak valid.\n");
			}
		}

		sc.close();
	}

	// Menampilkan identitas kelompok di awal program
	private static void tampilkanIdentitasKelompok() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy");

		System.out.println("============================================================");
		System.out.println("        SISTEM MANAJEMEN PLAYLIST MUSIK (OOP)");
		System.out.println("        COSC6025 - Data Structures and Algorithm Analysis");
		System.out.println("------------------------------------------------------------");
		System.out.println("Versi Program : 1.0");
		System.out.println("Tanggal       : " + today.format(fmt));
		System.out.println("------------------------------------------------------------");
		System.out.println("IDENTITAS KELOMPOK");
		System.out.println("1. Jimmy Sepriansyah ................ 2902706364");
		System.out.println("2. Muhammad Rizki Kurnia Putra ...... 2401968362");
		System.out.println("3. Charys Sonnia Indriyana .......... 2902728251");
		System.out.println("4. Shafa Alifia Salsabila Nurul Ichsan 2902697233");
		System.out.println("5. Anita ............................ 2902734600");
		System.out.println("============================================================\n");
	}

	// =========================
	// Menu Admin (fungsi utama admin)
	// =========================
	private static int menuAdmin(Scanner sc, Admin admin, Lagu[] playlist, int jumlahLagu) {
		boolean kembali = false;
		while (!kembali) {
			System.out.println("\n--- MENU ADMIN ---");
			System.out.println("1. Tambah Lagu");
			System.out.println("2. Lihat Daftar Lagu");
			System.out.println("0. Kembali");
			System.out.print("Input: ");

			int pilih = bacaInt(sc);

			switch (pilih) {
			case 1: {
				if (jumlahLagu >= playlist.length) {
					System.out.println("Playlist penuh. Tidak bisa menambah lagu.");
					break;
				}

				System.out.print("Judul: ");
				String judul = sc.nextLine().trim();

				System.out.print("Artis: ");
				String artis = sc.nextLine().trim();

				System.out.print("Durasi (menit, contoh 3.50): ");
				double durasi = bacaDouble(sc);

				boolean berhasil = admin.tambahLagu(playlist, jumlahLagu, new Lagu(judul, artis, durasi));
				if (berhasil) {
					jumlahLagu++;
					System.out.println("Lagu berhasil ditambahkan.");
				} else {
					System.out.println("Gagal menambahkan lagu.");
				}
				break;
			}
			case 2:
				tampilkanDaftar(playlist, jumlahLagu);
				break;

			case 0:
				kembali = true;
				break;

			default:
				System.out.println("Pilihan tidak valid.");
			}
		}
		return jumlahLagu;
	}

	// =========================
	// Menu Member (fungsi utama member)
	// =========================
	private static void menuMember(Scanner sc, Member member, Lagu[] playlist, int jumlahLagu) {
		boolean kembali = false;
		while (!kembali) {
			System.out.println("\n--- MENU MEMBER ---");
			System.out.println("1. Lihat Daftar Lagu");
			System.out.println("2. Cari Lagu (berdasarkan judul)");
			System.out.println("3. Hitung Rata-rata Durasi Lagu");
			System.out.println("0. Kembali");
			System.out.print("Input: ");

			int pilih = bacaInt(sc);

			switch (pilih) {
			case 1:
				member.lihatDaftar(playlist, jumlahLagu);
				break;

			case 2: {
				System.out.print("Masukkan judul yang dicari: ");
				String keyword = sc.nextLine().trim();
				int idx = member.cariLaguByJudul(playlist, jumlahLagu, keyword);

				if (idx == -1) {
					System.out.println("Lagu tidak ditemukan.");
				} else {
					System.out.println("Lagu ditemukan (detail):");
					playlist[idx].tampilkanInfo();
				}
				break;
			}

			case 3: {
				double avg = member.hitungRataRataDurasi(playlist, jumlahLagu);
				if (jumlahLagu == 0) {
					System.out.println("Playlist masih kosong, rata-rata tidak dapat dihitung.");
				} else {
					System.out.printf("Rata-rata durasi: %.2f menit%n", avg);
				}
				break;
			}

			case 0:
				kembali = true;
				break;

			default:
				System.out.println("Pilihan tidak valid.");
			}
		}
	}

	// =========================
	// Helper output & input
	// =========================
	private static void tampilkanDaftar(Lagu[] playlist, int jumlahLagu) {
		System.out.println("\n=== DAFTAR LAGU ===");
		if (jumlahLagu == 0) {
			System.out.println("(Kosong)");
			return;
		}
		for (int i = 0; i < jumlahLagu; i++) {
			Lagu l = playlist[i];
			System.out.printf("%d) %s - %s (%.2f menit)%n",
			                  (i + 1), l.getJudul(), l.getArtis(), l.getDurasi());
		}
	}

	private static int bacaInt(Scanner sc) {
		while (true) {
			String s = sc.nextLine().trim();
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.print("Input harus angka. Coba lagi: ");
			}
		}
	}

	private static double bacaDouble(Scanner sc) {
		while (true) {
			String s = sc.nextLine().trim();
			try {
				return Double.parseDouble(s);
			} catch (NumberFormatException e) {
				System.out.print("Input harus angka desimal (contoh 3.50). Coba lagi: ");
			}
		}
	}
}

// =====================================================
// Class Lagu (Encapsulation + tampilkanInfo)
// =====================================================
class Lagu {
	private String judul;
	private String artis;
	private double durasi; // menit

	public Lagu(String judul, String artis, double durasi) {
		this.judul = judul;
		this.artis = artis;
		this.durasi = durasi;
	}

	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getArtis() {
		return artis;
	}
	public void setArtis(String artis) {
		this.artis = artis;
	}

	public double getDurasi() {
		return durasi;
	}
	public void setDurasi(double durasi) {
		this.durasi = durasi;
	}

	public void tampilkanInfo() {
		System.out.println("Judul  : " + judul);
		System.out.println("Artis  : " + artis);
		System.out.printf("Durasi : %.2f menit%n", durasi);
	}
}

// =====================================================
// Parent class User (Inheritance base + Polymorphism target)
// =====================================================
class User {
	private String nama;

	public User(String nama) {
		this.nama = nama;
	}

	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}

	public void tampilkanAkses() {
		System.out.println("User umum: akses standar.");
	}
}

// =====================================================
// Admin extends User (Inheritance + Polymorphism override)
// =====================================================
class Admin extends User {
	public Admin(String nama) {
		super(nama);
	}

	@Override
	public void tampilkanAkses() {
		System.out.println("\n[Akses Admin] Bisa menambah lagu & melihat seluruh playlist.");
	}

	public boolean tambahLagu(Lagu[] playlist, int indexTujuan, Lagu laguBaru) {
		if (playlist == null || laguBaru == null) return false;
		if (indexTujuan < 0 || indexTujuan >= playlist.length) return false;

		playlist[indexTujuan] = laguBaru;
		return true;
	}
}

// =====================================================
// Member extends User (Inheritance + Polymorphism override)
// =====================================================
class Member extends User {
	public Member(String nama) {
		super(nama);
	}

	@Override
	public void tampilkanAkses() {
		System.out.println("\n[Akses Member] Bisa melihat daftar, cari lagu, dan hitung rata-rata durasi.");
	}

	public void lihatDaftar(Lagu[] playlist, int jumlahLagu) {
		System.out.println("\n=== DAFTAR LAGU (Member View) ===");
		if (jumlahLagu == 0) {
			System.out.println("(Kosong)");
			return;
		}
		for (int i = 0; i < jumlahLagu; i++) {
			System.out.printf("%d) %s - %s%n", (i + 1),
			                  playlist[i].getJudul(), playlist[i].getArtis());
		}
	}

	public int cariLaguByJudul(Lagu[] playlist, int jumlahLagu, String keyword) {
		if (keyword == null) return -1;
		String k = keyword.trim().toLowerCase();
		if (k.isEmpty()) return -1;

		for (int i = 0; i < jumlahLagu; i++) {
			String judul = playlist[i].getJudul();
			if (judul != null && judul.toLowerCase().contains(k)) {
				return i;
			}
		}
		return -1;
	}

	public double hitungRataRataDurasi(Lagu[] playlist, int jumlahLagu) {
		if (jumlahLagu <= 0) return 0.0;

		double total = 0.0;
		for (int i = 0; i < jumlahLagu; i++) {
			total += playlist[i].getDurasi();
		}
		return total / jumlahLagu;
	}
}