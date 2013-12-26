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
package org.itstechupnorth.sekhmet.audio.io.builder;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioIOWrangler {

    public void dumpAsIntegers(final File in)
            throws UnsupportedAudioFileException, IOException {
        AudioDataPipeline.source(in).to(System.out).start().perform();
    }

    public void asIntegers(final File in, final PrintStream out)
            throws UnsupportedAudioFileException, IOException {
        AudioDataPipeline.source(in).to(out).start().perform();
    }

    public void convert(final File in, final File out,
            final AudioFileFormat.Type to)
            throws UnsupportedAudioFileException, IOException {
        AudioDataPipeline.source(in).to(out, to).start().perform();
    }
}
