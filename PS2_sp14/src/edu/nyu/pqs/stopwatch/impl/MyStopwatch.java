package edu.nyu.pqs.stopwatch.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * @author jing.xia
 * 
 */
public class MyStopwatch implements IStopwatch {
	private String id;
	private Long startTime;
	private Long stopTime;
	//For record every lap's period time
	private List<Long> lapTimes;
	//For record every lap's system time
	private List<Long> lapSystemTime;
	private StopWatchStatus status;

	/**
	 * Initial MyStopwatch. 
	 * 
	 * @param sid
	 */
	public MyStopwatch(String sid) {
		this.id = sid;
		status = StopWatchStatus.INIT;
		startTime = -1l;
		stopTime = -1l;
		//use CopyOnWriteArrayList for thread safe, but seems there is no chance add/delete while iterating. Maybe better just use ArrayList so that not heavy?
		lapTimes = new CopyOnWriteArrayList<Long>();
		lapSystemTime = new CopyOnWriteArrayList<Long>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.nyu.pqs.stopwatch.api.IStopwatch#getId()
	 */
	@Override
	public String getId() {
		return this.id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.nyu.pqs.stopwatch.api.IStopwatch#start()
	 */
	@Override
	public void start() {
		if (status == StopWatchStatus.STARTED) {
			throw new IllegalStateException();
		} else {
			status = StopWatchStatus.STARTED;
			startTime = System.currentTimeMillis();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.nyu.pqs.stopwatch.api.IStopwatch#lap()
	 */
	@Override
	public void lap() {
		if (status == StopWatchStatus.STARTED) {
			//didn't check null for lapTimes because there is no chance to get null
			if (lapTimes.isEmpty()) {
				lapTimes.add(System.currentTimeMillis() - startTime);
				lapSystemTime.add(System.currentTimeMillis());
			} else {
				//not stop - start 
				if (stopTime == -1l) {
					lapTimes.add(System.currentTimeMillis()
							- lapSystemTime.get(lapSystemTime.size() - 1));
					lapSystemTime.add(System.currentTimeMillis());
				}
				// stop - start w/o reset 
				else {
					lapTimes.add(System.currentTimeMillis()
							- lapSystemTime.get(lapSystemTime.size() - 1)
							- (startTime - stopTime));
					lapSystemTime.add(System.currentTimeMillis());
					stopTime = -1l;
				}
			}
		} else {
			throw new IllegalStateException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.nyu.pqs.stopwatch.api.IStopwatch#stop()
	 */
	@Override
	public void stop() {
		if (status == StopWatchStatus.STARTED) {
			status = StopWatchStatus.STOPPED;
			stopTime = System.currentTimeMillis();
		} else {
			throw new IllegalStateException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.nyu.pqs.stopwatch.api.IStopwatch#reset()
	 */
	@Override
	public void reset() {
		status = StopWatchStatus.INIT;
		startTime = -1l;
		stopTime = -1l;
		lapTimes.clear();
		lapSystemTime.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.nyu.pqs.stopwatch.api.IStopwatch#getLapTimes()
	 */
	@Override
	public List<Long> getLapTimes() {
		return lapTimes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lapSystemTime == null) ? 0 : lapSystemTime.hashCode());
		result = prime * result
				+ ((lapTimes == null) ? 0 : lapTimes.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((stopTime == null) ? 0 : stopTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyStopwatch other = (MyStopwatch) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lapSystemTime == null) {
			if (other.lapSystemTime != null)
				return false;
		} else if (!lapSystemTime.equals(other.lapSystemTime))
			return false;
		if (lapTimes == null) {
			if (other.lapTimes != null)
				return false;
		} else if (!lapTimes.equals(other.lapTimes))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (stopTime == null) {
			if (other.stopTime != null)
				return false;
		} else if (!stopTime.equals(other.stopTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MyStopwatch [id=" + id + ", startTime=" + startTime
				+ ", stopTime=" + stopTime + ", lapTimes=" + lapTimes
				+ ", lapSystemTime=" + lapSystemTime + ", status=" + status
				+ "]";
	}
	
	

}
