package cn.kyt.ums.utils;


public class IdWorker {

	private final long workerId;
	private final long epoch = 1403854494756L;
	private final long workerIdBits = 10L;
	private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;

	private long sequence = 0L;
	private final long sequenceBits = 12L;

	private final long workerIdShift = this.sequenceBits; // 12
	private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits;
	private long lastTimestamp = -1L;

	public IdWorker(long workerId) {
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() throws Exception {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds",
					(this.lastTimestamp - timestamp)));
		}

		this.lastTimestamp = timestamp;
		return timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
	}

	private long tilNextMillis(long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private static long timeGen() {
		return System.currentTimeMillis();
	}

}
