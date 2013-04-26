package com.runnable.test;


import static org.junit.Assert.fail;

import com.runnable.SchoolRanking;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Locale;
import org.junit.Test;

public class SchoolRankingTest {
	// no visible side effects and never returns normally, so nothing to do but
	// let it run for a while and make sure there are no exceptions
	@Test
	@SuppressWarnings("deprecation")
	public void testMain() throws Throwable {
		ExceptionHandler exceptionHandler = new ExceptionHandler(
				Thread.currentThread());
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				SchoolRanking.main(null);
			}
		});
		t.setUncaughtExceptionHandler(exceptionHandler);
		
		PrintStream err = System.err;
		try {
			// watch to see if something uses System.err - probably a sign of an
			// error
			OutputHandler outputHandler = new OutputHandler(err,
					Thread.currentThread());
			System.setErr(outputHandler);
			
			t.start();
			try {
				Thread.sleep(15000);
			} catch (Exception e) {
			}
			t.stop(); // deprecated, yes, but this is a safe use
			t.join(5000);
			if (t.isAlive()) {
				t.stop();
				fail("Thread did not stop - must have caught ThreadDeath");
			}
			
			if (exceptionHandler.error != null &&
					exceptionHandler.error.getClass() != ThreadDeath.class)
				throw exceptionHandler.error;
			
			if (outputHandler.printed)
				fail("Printed to System.err");
		} finally {
			System.setErr(err); // restore System.err
		}
	}
	
	private class ExceptionHandler implements UncaughtExceptionHandler {
		private final Thread t;
		volatile Throwable error;
		
		ExceptionHandler(Thread t) {
			this.t = t;
		}
		
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			error = e;
			this.t.interrupt();
		}
	}
	
	private class OutputHandler extends PrintStream {
		private final Thread t;
		volatile boolean printed;
		
		OutputHandler(OutputStream out, Thread t) {
			super(out);
			this.t = t;
			printed = false;
		}
		
		@Override
		public PrintStream append(char c) {
			printed = true;
			t.interrupt();
			return super.append(c);
		}
		
		@Override
		public PrintStream append(CharSequence csq) {
			printed = true;
			t.interrupt();
			return super.append(csq);
		}
		
		@Override
		public PrintStream append(CharSequence csq, int start, int end) {
			printed = true;
			t.interrupt();
			return super.append(csq, start, end);
		}
		
		@Override
		public boolean checkError() {
			printed = true;
			t.interrupt();
			return super.checkError();
		}
		
		@Override
		public void close() {
			printed = true;
			t.interrupt();
			super.close();
		}
		
		@Override
		public void flush() {
			printed = true;
			t.interrupt();
			super.flush();
		}
		
		@Override
		public PrintStream format(Locale l, String format, Object... args) {
			printed = true;
			t.interrupt();
			return super.format(l, format, args);
		}
		
		@Override
		public PrintStream format(String format, Object... args) {
			printed = true;
			t.interrupt();
			return super.format(format, args);
		}
		
		@Override
		public void print(boolean b) {
			printed = true;
			t.interrupt();
			super.print(b);
		}
		
		@Override
		public void print(char c) {
			printed = true;
			t.interrupt();
			super.print(c);
		}
		
		@Override
		public void print(char[] s) {
			printed = true;
			t.interrupt();
			super.print(s);
		}
		
		@Override
		public void print(double d) {
			printed = true;
			t.interrupt();
			super.print(d);
		}
		
		@Override
		public void print(float f) {
			printed = true;
			t.interrupt();
			super.print(f);
		}
		
		@Override
		public void print(int i) {
			printed = true;
			t.interrupt();
			super.print(i);
		}
		
		@Override
		public void print(long l) {
			printed = true;
			t.interrupt();
			super.print(l);
		}
		
		@Override
		public void print(Object obj) {
			printed = true;
			t.interrupt();
			super.print(obj);
		}
		
		@Override
		public void print(String s) {
			printed = true;
			t.interrupt();
			super.print(s);
		}
		
		@Override
		public PrintStream printf(Locale l, String format, Object... args) {
			printed = true;
			t.interrupt();
			return super.printf(l, format, args);
		}
		
		@Override
		public PrintStream printf(String format, Object... args) {
			printed = true;
			t.interrupt();
			return super.printf(format, args);
		}
		
		@Override
		public void println() {
			printed = true;
			t.interrupt();
			super.println();
		}
		
		@Override
		public void println(boolean x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(char x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(char[] x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(double x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(float x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(int x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(long x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		public void println(Object x) {
			printed = true;
			t.interrupt();
			super.println(x);
		}
		
		@Override
		protected void setError() {
			printed = true;
			t.interrupt();
			super.setError();
		}
		
		@Override
		public void write(byte[] buf, int off, int len) {
			printed = true;
			t.interrupt();
			super.write(buf, off, len);
		}
		
		@Override
		public void write(int b) {
			printed = true;
			t.interrupt();
			super.write(b);
		}
		
	}
}
