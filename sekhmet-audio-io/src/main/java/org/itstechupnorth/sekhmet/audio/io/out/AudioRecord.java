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
package org.itstechupnorth.sekhmet.audio.io.out;

import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioInputStream;

import org.itstechupnorth.sekhmet.audio.io.AudioData;

public abstract class AudioRecord {

    protected final AudioFileFormat.Type type;

    public abstract AudioRecord write(final AudioInputStream stream)
            throws IOException;

    public abstract AudioRecord close();

    public AudioRecord write(final List<AudioData> data) throws IOException {
        if (data.size() > 0) {
            final AudioDataInputStream dataStream = new AudioDataInputStream(
                    data.iterator());
            final AudioInputStream audioDataStream = new AudioInputStream(
                    dataStream, data.get(0).getFormat(), AudioData.length(data));
            write(audioDataStream);
        }
        return this;
    }

    public AudioRecord(Type type) {
        super();
        this.type = type;
    }

    public AudioFileFormat.Type getType() {
        return type;
    }
}