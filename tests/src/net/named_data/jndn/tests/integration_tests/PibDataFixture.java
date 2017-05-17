/**
 * Copyright (C) 2015-2017 Regents of the University of California.
 * @author: Jeff Thompson <jefft0@remap.ucla.edu>
 * @author: From ndn-group-encrypt unit tests
 * https://github.com/named-data/ndn-cxx/blob/master/tests/unit-tests/security/pib/pib-data-fixture.cpp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * A copy of the GNU Lesser General Public License is in the file COPYING.
 */

package src.net.named_data.jndn.tests.integration_tests;

import java.nio.ByteBuffer;
import net.named_data.jndn.Name;
import net.named_data.jndn.encoding.EncodingException;
import net.named_data.jndn.security.pib.PibImpl;
import net.named_data.jndn.security.v2.CertificateV2;
import net.named_data.jndn.util.Blob;

public class PibDataFixture {
  // Convert the int array to a ByteBuffer.
  public static ByteBuffer
  toBuffer(int[] array)
  {
    ByteBuffer result = ByteBuffer.allocate(array.length);
    for (int i = 0; i < array.length; ++i)
      result.put((byte)(array[i] & 0xff));

    result.flip();
    return result;
  }

  private static final ByteBuffer ID1_KEY1_CERT1 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x25, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x01, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0xCB, 0x46, 0xF7, 0x16, 0x2E,
    0x83, 0x3D, 0x5E, 0x4A, 0x80, 0x6A, 0x78, 0xB7, 0xA8, 0x7A, 0x15, 0x95, 0x2D, 0x23, 0xA8, 0x41, 0xF7, 0x62, 0xE4, 0x0E,
    0x66, 0x36, 0xB3, 0xF3, 0x14, 0xD6, 0xB3, 0xAB, 0x19, 0x26, 0x9D, 0x5A, 0x8A, 0x51, 0xD4, 0x4E, 0xBA, 0xBE, 0x13, 0x96,
    0xCA, 0x38, 0x52, 0x16, 0xE4, 0x3D, 0xB0, 0x88, 0xBA, 0xBB, 0x7B, 0x97, 0x00, 0xA5, 0x95, 0x97, 0x4E, 0xE8, 0xF6, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x46, 0x30, 0x44, 0x02, 0x20, 0x53, 0xC8, 0xAD, 0x88, 0xBA, 0x52, 0x29, 0x68, 0xFF, 0x74, 0xA8, 0x39, 0x7F,
    0x2C, 0xE2, 0x8E, 0x04, 0xC1, 0x78, 0x36, 0x46, 0x89, 0x38, 0x58, 0x45, 0x22, 0x44, 0xA3, 0xC8, 0xC1, 0xFF, 0x72, 0x02,
    0x20, 0x23, 0x9D, 0xE4, 0x92, 0x00, 0xF1, 0x43, 0x69, 0xF7, 0x32, 0xF6, 0xAA, 0x8C, 0xFD, 0x7F, 0x2B, 0xFB, 0xD2, 0x40,
    0x6A, 0x1E, 0xA3, 0xE5, 0xF0, 0xF8, 0x2B, 0x92, 0x99, 0x6B, 0xDB, 0xE2, 0x6D
  });

  private static final ByteBuffer ID1_KEY1_CERT2 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x26, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x02, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0xCB, 0x46, 0xF7, 0x16, 0x2E,
    0x83, 0x3D, 0x5E, 0x4A, 0x80, 0x6A, 0x78, 0xB7, 0xA8, 0x7A, 0x15, 0x95, 0x2D, 0x23, 0xA8, 0x41, 0xF7, 0x62, 0xE4, 0x0E,
    0x66, 0x36, 0xB3, 0xF3, 0x14, 0xD6, 0xB3, 0xAB, 0x19, 0x26, 0x9D, 0x5A, 0x8A, 0x51, 0xD4, 0x4E, 0xBA, 0xBE, 0x13, 0x96,
    0xCA, 0x38, 0x52, 0x16, 0xE4, 0x3D, 0xB0, 0x88, 0xBA, 0xBB, 0x7B, 0x97, 0x00, 0xA5, 0x95, 0x97, 0x4E, 0xE8, 0xF6, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x47, 0x30, 0x45, 0x02, 0x21, 0x00, 0xB2, 0x93, 0xCD, 0x3D, 0x01, 0x00, 0xB5, 0xF1, 0x75, 0x22, 0x68, 0x9F,
    0xE4, 0x5E, 0x0A, 0x76, 0x34, 0xBC, 0x9D, 0xCF, 0x9A, 0x4C, 0x21, 0x3F, 0xA5, 0x12, 0x51, 0xF7, 0x3A, 0x5E, 0x37, 0x7D,
    0x02, 0x20, 0x33, 0xA9, 0xA9, 0x8F, 0xD8, 0x2E, 0xED, 0x3C, 0xE5, 0x18, 0x94, 0x59, 0x28, 0xEA, 0x82, 0x38, 0x5B, 0x20,
    0xE4, 0xBF, 0x15, 0xF4, 0x0D, 0x45, 0xAE, 0x8B, 0x63, 0x19, 0x79, 0x78, 0x50, 0x3A
  });

  private static final ByteBuffer ID1_KEY2_CERT1 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x25, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x01, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0x34, 0xAA, 0x4B, 0x1A, 0x97,
    0x4A, 0x6B, 0x6F, 0x3F, 0xB3, 0xC9, 0xD1, 0x39, 0x9F, 0x1E, 0x49, 0xB6, 0x6E, 0x19, 0x97, 0x13, 0x5E, 0xFA, 0xE6, 0xD3,
    0xFE, 0xF3, 0xB0, 0xCA, 0x80, 0x09, 0x31, 0xCA, 0x50, 0x5C, 0xE6, 0x57, 0xBF, 0x13, 0x16, 0xCE, 0x3E, 0xF1, 0xD4, 0x23,
    0xF8, 0x7F, 0x31, 0xFA, 0x13, 0x39, 0x09, 0xED, 0xC6, 0x74, 0x3D, 0xFD, 0x1A, 0x0B, 0xC7, 0xC1, 0x01, 0x15, 0x7F, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x46, 0x30, 0x44, 0x02, 0x20, 0x71, 0x3A, 0xB4, 0x19, 0x4B, 0xB3, 0x25, 0xA5, 0x03, 0x23, 0x8C, 0xC1, 0xB9,
    0x68, 0xC1, 0x41, 0x4B, 0xED, 0x13, 0xCC, 0x87, 0x16, 0xB5, 0x13, 0x87, 0xA0, 0x54, 0xA2, 0x9F, 0xF0, 0xD7, 0x72, 0x02,
    0x20, 0x4B, 0xEF, 0xB5, 0x6A, 0x8C, 0x40, 0x71, 0x17, 0xD2, 0x4F, 0xB6, 0x0F, 0xBE, 0x60, 0x1A, 0x46, 0x9B, 0x78, 0x15,
    0x46, 0x09, 0xC2, 0x7A, 0x80, 0xD4, 0xE6, 0x71, 0x52, 0xD6, 0x83, 0x4B, 0x04
  });

  private static final ByteBuffer ID1_KEY2_CERT2 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x26, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x02, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0x34, 0xAA, 0x4B, 0x1A, 0x97,
    0x4A, 0x6B, 0x6F, 0x3F, 0xB3, 0xC9, 0xD1, 0x39, 0x9F, 0x1E, 0x49, 0xB6, 0x6E, 0x19, 0x97, 0x13, 0x5E, 0xFA, 0xE6, 0xD3,
    0xFE, 0xF3, 0xB0, 0xCA, 0x80, 0x09, 0x31, 0xCA, 0x50, 0x5C, 0xE6, 0x57, 0xBF, 0x13, 0x16, 0xCE, 0x3E, 0xF1, 0xD4, 0x23,
    0xF8, 0x7F, 0x31, 0xFA, 0x13, 0x39, 0x09, 0xED, 0xC6, 0x74, 0x3D, 0xFD, 0x1A, 0x0B, 0xC7, 0xC1, 0x01, 0x15, 0x7F, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x31, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x47, 0x30, 0x45, 0x02, 0x21, 0x00, 0xD2, 0x90, 0x8C, 0xA3, 0x52, 0x2F, 0x79, 0xB3, 0xD7, 0x39, 0xE1, 0x6C,
    0x7F, 0xA0, 0xDF, 0xD1, 0x3E, 0x0F, 0x70, 0xBE, 0xF5, 0xDB, 0x08, 0xDF, 0xE1, 0x0B, 0xDF, 0x79, 0x99, 0xFE, 0x5C, 0xDC,
    0x02, 0x20, 0x3D, 0xD4, 0x7C, 0xD1, 0x83, 0xBE, 0x29, 0xBB, 0x73, 0xA3, 0x82, 0xE5, 0xE6, 0x83, 0xA1, 0xC1, 0xBC, 0xF6,
    0x84, 0x42, 0x85, 0x00, 0x92, 0x4B, 0xA2, 0xA8, 0xCA, 0x10, 0x7B, 0x92, 0x89, 0xB0
  });

  private static final ByteBuffer ID2_KEY1_CERT1 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x25, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x01, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0xAC, 0x62, 0xD7, 0x31, 0x3B,
    0x19, 0x1F, 0x44, 0x76, 0x6E, 0x79, 0x03, 0xB9, 0xC8, 0x26, 0xC4, 0x1E, 0x38, 0x3A, 0x41, 0xFE, 0xB4, 0x72, 0xA2, 0x36,
    0xBB, 0x82, 0x9C, 0xB9, 0x07, 0x62, 0x6F, 0x1C, 0x79, 0x12, 0xCA, 0x9C, 0x3D, 0xAA, 0x7A, 0x96, 0xFF, 0xAF, 0x5B, 0x6F,
    0xE1, 0x72, 0x60, 0xB0, 0x7F, 0x44, 0x38, 0x05, 0x21, 0xCC, 0x49, 0x78, 0x89, 0xC1, 0xEF, 0xEE, 0x81, 0x8E, 0xF5, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x46, 0x30, 0x44, 0x02, 0x20, 0x16, 0xC0, 0xF4, 0xE3, 0x15, 0x43, 0x6E, 0x27, 0x33, 0x7C, 0x46, 0x4D, 0x35,
    0xA7, 0x8B, 0x0C, 0xE3, 0x27, 0x63, 0x4B, 0xB2, 0xB6, 0x4F, 0x06, 0x90, 0x2A, 0xD8, 0x54, 0x92, 0xE8, 0xBA, 0xBE, 0x02,
    0x20, 0x67, 0xA6, 0x55, 0x8D, 0x16, 0x0E, 0x1E, 0x9E, 0x10, 0x0E, 0xB9, 0x3C, 0xEF, 0xEE, 0xB5, 0xF7, 0x9C, 0xB3, 0x1D,
    0x04, 0xF1, 0xD4, 0xB5, 0x9F, 0xD4, 0x13, 0xBB, 0xFF, 0xA7, 0x58, 0xAE, 0xCB
  });

  private static final ByteBuffer ID2_KEY1_CERT2 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x26, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x02, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0xAC, 0x62, 0xD7, 0x31, 0x3B,
    0x19, 0x1F, 0x44, 0x76, 0x6E, 0x79, 0x03, 0xB9, 0xC8, 0x26, 0xC4, 0x1E, 0x38, 0x3A, 0x41, 0xFE, 0xB4, 0x72, 0xA2, 0x36,
    0xBB, 0x82, 0x9C, 0xB9, 0x07, 0x62, 0x6F, 0x1C, 0x79, 0x12, 0xCA, 0x9C, 0x3D, 0xAA, 0x7A, 0x96, 0xFF, 0xAF, 0x5B, 0x6F,
    0xE1, 0x72, 0x60, 0xB0, 0x7F, 0x44, 0x38, 0x05, 0x21, 0xCC, 0x49, 0x78, 0x89, 0xC1, 0xEF, 0xEE, 0x81, 0x8E, 0xF5, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x01, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x47, 0x30, 0x45, 0x02, 0x21, 0x00, 0xF2, 0x0D, 0x1D, 0x60, 0x0B, 0x2D, 0x97, 0x3A, 0x6B, 0xEE, 0xEC, 0x56,
    0xD1, 0x64, 0xBF, 0xED, 0x68, 0xB7, 0x10, 0x0B, 0xDF, 0x81, 0x29, 0xCD, 0xB0, 0xBB, 0x87, 0x0D, 0xDA, 0x12, 0x52, 0xCC,
    0x02, 0x20, 0x64, 0x33, 0x4E, 0x91, 0xAF, 0x81, 0xF4, 0xE7, 0xAD, 0x38, 0x8E, 0xBF, 0x79, 0xA7, 0x70, 0x1E, 0xD6, 0x71,
    0x7E, 0xF5, 0xEB, 0x92, 0x56, 0x5F, 0xC7, 0x05, 0xDC, 0x27, 0xE5, 0x11, 0xC2, 0x43
  });

  private static final ByteBuffer ID2_KEY2_CERT1 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x26, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x01, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0x2C, 0xC3, 0xAF, 0xA8, 0x73,
    0xF2, 0x61, 0xCF, 0x48, 0x04, 0x0F, 0x9D, 0xD3, 0xAF, 0x0B, 0xC6, 0x2F, 0x4D, 0xDA, 0x0E, 0x4C, 0x66, 0x1D, 0x03, 0x9D,
    0xFE, 0x2C, 0x0B, 0xB6, 0x25, 0x60, 0xBC, 0xFA, 0xDA, 0xFE, 0x6F, 0x43, 0xFA, 0x95, 0x45, 0x57, 0x8A, 0x25, 0xEC, 0x0F,
    0xF2, 0xB7, 0x43, 0x85, 0x0D, 0x0B, 0x8D, 0x97, 0x40, 0x83, 0x4C, 0x28, 0x1B, 0xD4, 0x2E, 0x99, 0x2C, 0x73, 0x7D, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x47, 0x30, 0x45, 0x02, 0x20, 0x56, 0x34, 0x49, 0xAC, 0x72, 0x4E, 0x58, 0x24, 0x6F, 0x14, 0xEE, 0xD3, 0x01,
    0x5B, 0xD4, 0x0A, 0x26, 0x2B, 0x6A, 0xD1, 0xB3, 0x33, 0x69, 0x4D, 0x64, 0x0C, 0xAA, 0xAE, 0x63, 0x59, 0x6A, 0xFD, 0x02,
    0x21, 0x00, 0xFD, 0xB9, 0x9E, 0x37, 0x70, 0x9C, 0xE2, 0x7A, 0x0A, 0xFD, 0x64, 0x99, 0x1B, 0xA3, 0x78, 0x83, 0x09, 0xC6,
    0xA0, 0x6D, 0x7A, 0x55, 0x8F, 0x6C, 0x35, 0xAB, 0x63, 0x78, 0x9D, 0xF3, 0xDC, 0xBC
  });

  private static final ByteBuffer ID2_KEY2_CERT2 = toBuffer(new int[] {
    0x06, 0xFD, 0x02, 0x27, 0x07, 0x2B, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72, 0x66, 0x61,
    0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0x08, 0x06, 0x69,
    0x73, 0x73, 0x75, 0x65, 0x72, 0x08, 0x02, 0xFD, 0x02, 0x14, 0x09, 0x18, 0x01, 0x02, 0x19, 0x04, 0x00, 0x36, 0xEE, 0x80,
    0x15, 0xFD, 0x01, 0x4F, 0x30, 0x82, 0x01, 0x4B, 0x30, 0x82, 0x01, 0x03, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x02,
    0x01, 0x30, 0x81, 0xF7, 0x02, 0x01, 0x01, 0x30, 0x2C, 0x06, 0x07, 0x2A, 0x86, 0x48, 0xCE, 0x3D, 0x01, 0x01, 0x02, 0x21,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x30, 0x5B, 0x04, 0x20, 0xFF, 0xFF, 0xFF,
    0xFF, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF,
    0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFC, 0x04, 0x20, 0x5A, 0xC6, 0x35, 0xD8, 0xAA, 0x3A, 0x93, 0xE7, 0xB3,
    0xEB, 0xBD, 0x55, 0x76, 0x98, 0x86, 0xBC, 0x65, 0x1D, 0x06, 0xB0, 0xCC, 0x53, 0xB0, 0xF6, 0x3B, 0xCE, 0x3C, 0x3E, 0x27,
    0xD2, 0x60, 0x4B, 0x03, 0x15, 0x00, 0xC4, 0x9D, 0x36, 0x08, 0x86, 0xE7, 0x04, 0x93, 0x6A, 0x66, 0x78, 0xE1, 0x13, 0x9D,
    0x26, 0xB7, 0x81, 0x9F, 0x7E, 0x90, 0x04, 0x41, 0x04, 0x6B, 0x17, 0xD1, 0xF2, 0xE1, 0x2C, 0x42, 0x47, 0xF8, 0xBC, 0xE6,
    0xE5, 0x63, 0xA4, 0x40, 0xF2, 0x77, 0x03, 0x7D, 0x81, 0x2D, 0xEB, 0x33, 0xA0, 0xF4, 0xA1, 0x39, 0x45, 0xD8, 0x98, 0xC2,
    0x96, 0x4F, 0xE3, 0x42, 0xE2, 0xFE, 0x1A, 0x7F, 0x9B, 0x8E, 0xE7, 0xEB, 0x4A, 0x7C, 0x0F, 0x9E, 0x16, 0x2B, 0xCE, 0x33,
    0x57, 0x6B, 0x31, 0x5E, 0xCE, 0xCB, 0xB6, 0x40, 0x68, 0x37, 0xBF, 0x51, 0xF5, 0x02, 0x21, 0x00, 0xFF, 0xFF, 0xFF, 0xFF,
    0x00, 0x00, 0x00, 0x00, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xBC, 0xE6, 0xFA, 0xAD, 0xA7, 0x17, 0x9E, 0x84,
    0xF3, 0xB9, 0xCA, 0xC2, 0xFC, 0x63, 0x25, 0x51, 0x02, 0x01, 0x01, 0x03, 0x42, 0x00, 0x04, 0x2C, 0xC3, 0xAF, 0xA8, 0x73,
    0xF2, 0x61, 0xCF, 0x48, 0x04, 0x0F, 0x9D, 0xD3, 0xAF, 0x0B, 0xC6, 0x2F, 0x4D, 0xDA, 0x0E, 0x4C, 0x66, 0x1D, 0x03, 0x9D,
    0xFE, 0x2C, 0x0B, 0xB6, 0x25, 0x60, 0xBC, 0xFA, 0xDA, 0xFE, 0x6F, 0x43, 0xFA, 0x95, 0x45, 0x57, 0x8A, 0x25, 0xEC, 0x0F,
    0xF2, 0xB7, 0x43, 0x85, 0x0D, 0x0B, 0x8D, 0x97, 0x40, 0x83, 0x4C, 0x28, 0x1B, 0xD4, 0x2E, 0x99, 0x2C, 0x73, 0x7D, 0x16,
    0x50, 0x1B, 0x01, 0x03, 0x1C, 0x21, 0x07, 0x1F, 0x08, 0x03, 0x70, 0x69, 0x62, 0x08, 0x09, 0x69, 0x6E, 0x74, 0x65, 0x72,
    0x66, 0x61, 0x63, 0x65, 0x08, 0x02, 0x69, 0x64, 0x08, 0x01, 0x32, 0x08, 0x03, 0x4B, 0x45, 0x59, 0x08, 0x01, 0x02, 0xFD,
    0x00, 0xFD, 0x26, 0xFD, 0x00, 0xFE, 0x0F, 0x32, 0x30, 0x31, 0x37, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x30, 0xFD, 0x00, 0xFF, 0x0F, 0x32, 0x30, 0x31, 0x38, 0x30, 0x31, 0x30, 0x32, 0x54, 0x30, 0x30, 0x30, 0x30, 0x30,
    0x30, 0x17, 0x48, 0x30, 0x46, 0x02, 0x21, 0x00, 0xB3, 0xF5, 0x96, 0x19, 0xE7, 0xF9, 0x6B, 0xCF, 0x14, 0x64, 0xB1, 0x08,
    0xFA, 0xFF, 0xB3, 0x52, 0x8B, 0x41, 0xCB, 0xE7, 0xE0, 0x3D, 0x14, 0x7B, 0xC2, 0xD0, 0xC8, 0x89, 0x88, 0xFA, 0x95, 0x73,
    0x02, 0x21, 0x00, 0xEC, 0x8E, 0x0C, 0x8B, 0x8C, 0x18, 0x8D, 0x00, 0x7C, 0x12, 0x68, 0x57, 0x87, 0xB1, 0x99, 0x69, 0xDA,
    0x46, 0xEF, 0x14, 0x2D, 0x04, 0x18, 0xBE, 0x1D, 0xAE, 0x79, 0x49, 0xFD, 0x22, 0x8E, 0xBB
  });


  static CertificateV2
  encodeCertificate(ByteBuffer buffer) throws EncodingException
  {
    CertificateV2 result = new CertificateV2();
    result.wireDecode(buffer);
    return result;
  }

  public PibDataFixture() throws EncodingException, CertificateV2.Error
  {
    id1Key1Cert1 = encodeCertificate(ID1_KEY1_CERT1);
    id1Key1Cert2 = encodeCertificate(ID1_KEY1_CERT2);
    id1Key2Cert1 = encodeCertificate(ID1_KEY2_CERT1);
    id1Key2Cert2 = encodeCertificate(ID1_KEY2_CERT2);

    id2Key1Cert1 = encodeCertificate(ID2_KEY1_CERT1);
    id2Key1Cert2 = encodeCertificate(ID2_KEY1_CERT2);
    id2Key2Cert1 = encodeCertificate(ID2_KEY2_CERT1);
    id2Key2Cert2 = encodeCertificate(ID2_KEY2_CERT2);

    id1 = id1Key1Cert1.getIdentity();
    id2 = id2Key1Cert1.getIdentity();

    id1Key1Name = id1Key1Cert1.getKeyName();
    id1Key2Name = id1Key2Cert1.getKeyName();

    id2Key1Name = id2Key1Cert1.getKeyName();
    id2Key2Name = id2Key2Cert1.getKeyName();

    id1Key1 = id1Key1Cert1.getPublicKey();
    id1Key2 = id1Key2Cert1.getPublicKey();
    id2Key1 = id2Key1Cert1.getPublicKey();
    id2Key2 = id2Key2Cert1.getPublicKey();
  }

  public PibImpl pib;

  public CertificateV2 id1Key1Cert1;
  public CertificateV2 id1Key1Cert2;
  public CertificateV2 id1Key2Cert1;
  public CertificateV2 id1Key2Cert2;
  public CertificateV2 id2Key1Cert1;
  public CertificateV2 id2Key1Cert2;
  public CertificateV2 id2Key2Cert1;
  public CertificateV2 id2Key2Cert2;

  public Name id1;
  public Name id2;

  public Name id1Key1Name;
  public Name id1Key2Name;
  public Name id2Key1Name;
  public Name id2Key2Name;

  public Blob id1Key1;
  public Blob id1Key2;
  public Blob id2Key1;
  public Blob id2Key2;
}
