import librosa
import soundfile as sf
import numpy as np

# 音符列表
notes = ['A0', 'AS0', 'B0', 
         'C1', 'CS1', 'D1', 'DS1', 'E1', 'F1', 'FS1', 'G1', 'GS1', 'A1', 'AS1', 'B1', 
         'C2', 'CS2', 'D2', 'DS2', 'E2', 'F2', 'FS2', 'G2', 'GS2', 'A2', 'AS2', 'B2', 
         'C3', 'CS3', 'D3', 'DS3', 'E3', 'F3', 'FS3', 'G3', 'GS3', 'A3', 'AS3', 'B3', 
         'C4', 'CS4', 'D4', 'DS4', 'E4', 'F4', 'FS4', 'G4', 'GS4', 'A4', 'AS4', 'B4', 
         'C5', 'CS5', 'D5', 'DS5', 'E5', 'F5', 'FS5', 'G5', 'GS5', 'A5', 'AS5', 'B5', 
         'C6', 'CS6', 'D6', 'DS6', 'E6', 'F6', 'FS6', 'G6', 'GS6', 'A6', 'AS6', 'B6', 
         'C7', 'CS7', 'D7', 'DS7', 'E7', 'F7', 'FS7', 'G7', 'GS7', 'A7', 'AS7', 'B7', 
         'C8']

# 读取音频文件
y, sr = librosa.load('source.ogg', sr=44100)

# 原始音（FS4）在音符列表中的位置
original_note_index = notes.index('FS4')

for i, note in enumerate(notes):
    # 计算需要变调的半音数
    half_tones = i - original_note_index

    # 变调
    y_pitch_shift = librosa.effects.pitch_shift(y=y, sr=sr, n_steps=half_tones)

    # 伸缩
    speed_rate = 2 ** (half_tones / 12)
    y_stretch = librosa.effects.time_stretch(y=y_pitch_shift, rate=speed_rate)

    # 保存变调和伸缩后的音频文件
    sf.write(f"{i}_{note}.ogg", y_stretch, sr)
