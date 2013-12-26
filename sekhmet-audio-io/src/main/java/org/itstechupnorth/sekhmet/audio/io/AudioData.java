/*
 *  Copyright 2010-2013 Robert Burrell Donkin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.itstechupnorth.sekhmet.audio.io;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Collection;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public class AudioData {

    public static final long length(Collection<AudioData> data) {
        long count = 0;
        for (final AudioData datum : data) {
            count += datum.length();
        }
        return count;
    }

    private final ByteBuffer buffer;
    private final AudioFormat format;
    private final long startFrame;
    private final long startMillis;
    private final long numberOfFrames;
    private final long durationMillis;

    public AudioData(ByteBuffer buffer, AudioFormat format, long startFrame,
            long startMillis, long numberOfFrames, long durationMillis) {
        super();
        this.buffer = buffer;
        if (format.isBigEndian()) {
            buffer.order(ByteOrder.BIG_ENDIAN);
        } else {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        this.format = format;
        this.startFrame = startFrame;
        this.startMillis = startMillis;
        this.numberOfFrames = numberOfFrames;
        this.durationMillis = durationMillis;
    }

    public AudioFormat getFormat() {
        return format;
    }

    public void writeTo(final SourceDataLine line) {
        line.write(buffer.array(), buffer.arrayOffset(), buffer.limit());
    }

    public long getStartMillis() {
        return startMillis;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    public long getStartFrame() {
        return startFrame;
    }

    public long getNumberOfFrames() {
        return numberOfFrames;
    }

    public long length() {
        return buffer.limit();
    }

    public void trace(PrintStream out) {
        out.print("[start: " + getStartFrame() + " (" + getStartMillis()
                + "), duration:" + getNumberOfFrames() + " ("
                + getDurationMillis() + "), bytes:" + buffer.remaining() + "]");
    }

    public ByteArrayInputStream inputStream() {
        return new ByteArrayInputStream(buffer.array(), 0, buffer.limit());
    }

    public IntBuffer asIntegers() {
        return buffer.asIntBuffer().asReadOnlyBuffer();
    }

    @Override
    public String toString() {
        return "AudioData [buffer=" + buffer + ", durationMillis="
                + durationMillis + ", format=" + format + ", numberOfFrames="
                + numberOfFrames + ", startFrame=" + startFrame
                + ", startMillis=" + startMillis + "]";
    }
}
