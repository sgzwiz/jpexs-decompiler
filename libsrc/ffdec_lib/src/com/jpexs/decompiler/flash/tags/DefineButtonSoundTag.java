/*
 *  Copyright (C) 2010-2015 JPEXS, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.jpexs.decompiler.flash.tags;

import com.jpexs.decompiler.flash.SWF;
import com.jpexs.decompiler.flash.SWFInputStream;
import com.jpexs.decompiler.flash.SWFOutputStream;
import com.jpexs.decompiler.flash.tags.base.CharacterIdTag;
import com.jpexs.decompiler.flash.types.BasicType;
import com.jpexs.decompiler.flash.types.SOUNDINFO;
import com.jpexs.decompiler.flash.types.annotations.SWFType;
import com.jpexs.helpers.ByteArrayRange;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 *
 * @author JPEXS
 */
public class DefineButtonSoundTag extends CharacterIdTag {

    @SWFType(BasicType.UI16)
    public int buttonId;

    @SWFType(BasicType.UI16)
    public int buttonSoundChar0;

    public SOUNDINFO buttonSoundInfo0;

    @SWFType(BasicType.UI16)
    public int buttonSoundChar1;

    public SOUNDINFO buttonSoundInfo1;

    @SWFType(BasicType.UI16)
    public int buttonSoundChar2;

    public SOUNDINFO buttonSoundInfo2;

    @SWFType(BasicType.UI16)
    public int buttonSoundChar3;

    public SOUNDINFO buttonSoundInfo3;

    public static final int ID = 17;

    @Override
    public int getCharacterId() {
        return buttonId;
    }

    /**
     * Gets data bytes
     *
     * @return Bytes of data
     */
    @Override
    public byte[] getData() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = baos;
        SWFOutputStream sos = new SWFOutputStream(os, getVersion());
        try {
            sos.writeUI16(buttonId);
            sos.writeUI16(buttonSoundChar0);
            if (buttonSoundChar0 != 0) {
                sos.writeSOUNDINFO(buttonSoundInfo0);
            }
            sos.writeUI16(buttonSoundChar1);
            if (buttonSoundChar1 != 0) {
                sos.writeSOUNDINFO(buttonSoundInfo1);
            }
            sos.writeUI16(buttonSoundChar2);
            if (buttonSoundChar2 != 0) {
                sos.writeSOUNDINFO(buttonSoundInfo2);
            }
            sos.writeUI16(buttonSoundChar3);
            if (buttonSoundChar3 != 0) {
                sos.writeSOUNDINFO(buttonSoundInfo3);
            }
        } catch (IOException e) {
            throw new Error("This should never happen.", e);
        }
        return baos.toByteArray();
    }

    /**
     * Constructor
     *
     * @param swf
     */
    public DefineButtonSoundTag(SWF swf) {
        super(swf, ID, "DefineButtonSound", null);
    }

    /**
     * Constructor
     *
     * @param sis
     * @param data
     * @throws IOException
     */
    public DefineButtonSoundTag(SWFInputStream sis, ByteArrayRange data) throws IOException {
        super(sis.getSwf(), ID, "DefineButtonSound", data);
        readData(sis, data, 0, false, false, false);
    }

    @Override
    public final void readData(SWFInputStream sis, ByteArrayRange data, int level, boolean parallel, boolean skipUnusualTags, boolean lazy) throws IOException {
        buttonId = sis.readUI16("buttonId");
        buttonSoundChar0 = sis.readUI16("buttonSoundChar0");
        if (buttonSoundChar0 != 0) {
            buttonSoundInfo0 = sis.readSOUNDINFO("buttonSoundInfo0");
        }
        buttonSoundChar1 = sis.readUI16("buttonSoundChar1");
        if (buttonSoundChar1 != 0) {
            buttonSoundInfo1 = sis.readSOUNDINFO("buttonSoundInfo1");
        }
        buttonSoundChar2 = sis.readUI16("buttonSoundChar2");
        if (buttonSoundChar2 != 0) {
            buttonSoundInfo2 = sis.readSOUNDINFO("buttonSoundInfo2");
        }
        buttonSoundChar3 = sis.readUI16("buttonSoundChar3");
        if (buttonSoundChar3 != 0) {
            buttonSoundInfo3 = sis.readSOUNDINFO("buttonSoundInfo3");
        }
    }
}
