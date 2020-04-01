import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Lives extends Frame implements Runnable {
	LivesCanvas c;
	Boolean spot[][] = new Boolean[21][21];
	Thread t;

	public Lives() {
		super();
		int x, y, tmp;
		Random rnd = new Random();
		for (x = 0; x < 21; x++) {
			for (y = 0; y < 21; y++) {
				tmp = rnd.nextInt(3);
				if (tmp == 0)
					spot[x][y] = true;
				else
					spot[x][y] = false;
			}
		}
		setTitle("Lives");
		setSize(398, 420);
		setResizable(false);
		setLocation(32, 48);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		c = new LivesCanvas();
		c.setBounds(0, 0, 401, 401);
		this.add(c);

		t = new Thread(this);
		t.start();
	}

	public static void main(String[] args) {
		Lives livesWindow;
		livesWindow = new Lives();
		livesWindow.show();
	}

	public void run() {
		Boolean tmp[][] = new Boolean[21][21];
		while (true) {
			int x, y, live;
			for (x = 0; x < 21; x++) {
				for (y = 0; y < 21; y++) {
					tmp[x][y] = spot[x][y];
				}
			}
			for (x = 0; x < 21; x++) {
				for (y = 0; y < 21; y++) {
					live = 0;
					if (x > 0) {
						if (tmp[x - 1][y] == true)
							live++;
					}
					if (y > 0) {
						if (tmp[x][y - 1] == true)
							live++;
					}
					if (x < 20) {
						if (tmp[x + 1][y] == true)
							live++;
					}
					if (y < 20) {
						if (tmp[x][y + 1] == true)
							live++;
					}
					if (x > 0 && y > 0) {
						if (tmp[x - 1][y - 1] == true)
							live++;
					}
					if (x > 0 && y < 20) {
						if (tmp[x - 1][y + 1] == true)
							live++;
					}
					if (x < 20 && y < 20) {
						if (tmp[x + 1][y + 1] == true)
							live++;
					}
					if (x < 20 && y > 0) {
						if (tmp[x + 1][y - 1] == true)
							live++;
					}
					if (tmp[x][y] == true) {
						if (live == 2 || live == 3)
							spot[x][y] = true;
						else
							spot[x][y] = false;
					} else {
						if (live == 3)
							spot[x][y] = true;
						else
							spot[x][y] = false;
					}
				}
			}
			c.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	class LivesCanvas extends Canvas implements MouseListener {
		public LivesCanvas() {
			super();
			addMouseListener(this);
		}

		public void paint(final Graphics g) {
			int x, y;
			g.setColor(Color.gray);
			g.fillRect(0, 0, 401, 401);
			for (x = 0; x < 21; x++) {
				for (y = 0; y < 21; y++) {
					if (spot[x][y] == true)
						g.setColor(Color.black);
					else
						g.setColor(Color.white);
					g.fillRect(x * 19, y * 19, 18, 18);
				}
			}
		}

		public void mouseClicked(final MouseEvent ev) {

		}

		public void mouseEntered(final MouseEvent ev) {

		}

		public void mouseExited(final MouseEvent ev) {

		}

		public void mousePressed(final MouseEvent ev) {
			int x, y;
			x = ev.getX() / 19;
			y = ev.getY() / 19;
			if (x < 0 || y < 0 || x > 20 || y > 20)
				return;
			spot[x][y] = true;
			c.repaint();
		}

		public void mouseReleased(final MouseEvent ev) {

		}
	}
}
