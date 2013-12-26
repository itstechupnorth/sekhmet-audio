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

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

class FileAudioRecord extends AudioRecord {

    public static final int NONE = -1;
    private final File out;
    private int bytesWritten = NONE;

    public FileAudioRecord(final File out, final AudioFileFormat.Type type) {
        super(type);
        this.out = out;
    }

    @Override
    public AudioRecord close() {
        return this;
    }

    public int getBytesWritten() {
        return bytesWritten;
    }

    @Override
    public AudioRecord write(final AudioInputStream stream) throws IOException {
        bytesWritten = AudioSystem.write(stream, type, out);
        return this;
    }

    @Override
    public String toString() {
        return "FileAudioRecord [bytesWritten=" + bytesWritten + ", out=" + out
                + "]";
    }
}
