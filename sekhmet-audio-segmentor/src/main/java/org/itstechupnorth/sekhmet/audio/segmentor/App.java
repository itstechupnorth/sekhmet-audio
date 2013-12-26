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
package org.itstechupnorth.sekhmet.audio.segmentor;

import java.io.File;

import org.itstechupnorth.sekhmet.audio.io.builder.AudioIOWrangler;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println( Constants.DESCRIPTION );
        // long start = System.currentTimeMillis();
        // new AudioIOWrangler().convert(new File("src/test/resources/in.wav"),
        // new File("src/test/resources/out.wav"), AudioFileFormat.Type.WAVE);
        new AudioIOWrangler().dumpAsIntegers(new File(
                "src/test/resources/in.wav"));
        // System.out.println("Time taken: " + (System.currentTimeMillis() -
        // start) / 1000f + " (seconds)");
    }
}
