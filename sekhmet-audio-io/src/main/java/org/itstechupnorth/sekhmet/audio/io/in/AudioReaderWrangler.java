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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import name.robertburrelldonkin.alfie.Message;
import name.robertburrelldonkin.alfie.Source;

import org.itstechupnorth.sekhmet.audio.io.AudioData;
import org.itstechupnorth.sekhmet.audio.io.Constants;

public class AudioReaderWrangler {

    private int bufferSize = Constants.DEFAULT_INPUT_BUFFER_SIZE;

    public int getBufferSize() {
        return bufferSize;
    }

    public AudioReaderWrangler setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public AudioReader read(final TargetDataLine line) {
        return new LineReader(line);
    }

    public AudioReader read(final AudioInputStream in) {
        return new StreamReader(in, bufferSize);
    }

    public Source source(final AudioInputStream in,
            BlockingQueue<Message<AudioData>> out) {
        return new AudioReaderSource(out, read(in));
    }

    public Source source(final File file, BlockingQueue<Message<AudioData>> out)
            throws UnsupportedAudioFileException, IOException {
        return source(AudioSystem.getAudioInputStream(file), out);
    }
}
