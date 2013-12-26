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
package org.itstechupnorth.sekhmet.audio.io.in;

import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;

import org.itstechupnorth.sekhmet.audio.io.AudioData;

abstract class BufferingReader extends DefaultReader {

    public BufferingReader() {
        super();
    }

    protected AudioData build(final AudioFormat format, final long startFrame,
            final byte[] bytes, int numberOfBytesRead) {
        final long startMillis = Math.round((startFrame * 1000)
                / format.getFrameRate());
        final long numberOfFrames = numberOfBytesRead / format.getFrameSize();
        final long durationMillis = Math.round((numberOfFrames * 1000d)
                / format.getFrameRate());

        final ByteBuffer buffer = ByteBuffer.wrap(bytes, 0, numberOfBytesRead);
        return new AudioData(buffer, format, startFrame, startMillis,
                numberOfFrames, durationMillis);
    }

    protected long nextFrame(final AudioData data) {
        return data.getStartFrame() + data.getNumberOfFrames();
    }

}