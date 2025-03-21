import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  TouchableOpacity,
  SafeAreaView,
} from 'react-native';

const courses = [
  ['UX исследователь', 'Веб-дизайн', 'Cinema 4D', 'Промпт инженер', 'Three.js'],
  ['Контент маркетинг', 'B2B маркетинг', 'Google Аналитика', 'RabbitMQ', 'Big Data'],
  ['C Администрирование', 'Веб-аналитика', 'Парсинг', 'Python-разработка', 'UI/UX'],
  ['JavaScript', 'React Native', 'Node.js', 'Flutter', 'Swift'],
  ['SEO продвижение', 'Таргетинг', 'SMM менеджер', 'PR менеджер', 'Копирайтинг'],
];

const OnboardingScreen = () => {
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.titleContainer}>
        <Text style={styles.title}>
          Тысячи курсов{'\n'}в одном месте
        </Text>
      </View>

      <View style={styles.coursesContainer}>
        {courses.map((row, index) => (
          <ScrollView
            key={index}
            horizontal
            showsHorizontalScrollIndicator={false}
            style={styles.scrollView}
          >
            {row.map((course, courseIndex) => (
              <View
                key={courseIndex}
                style={styles.courseItem}
              >
                <Text style={styles.courseText}>{course}</Text>
              </View>
            ))}
          </ScrollView>
        ))}
      </View>

      <TouchableOpacity style={styles.button}>
        <Text style={styles.buttonText}>Продолжить</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000000',
  },
  titleContainer: {
    alignItems: 'center',
    marginTop: 40,
    marginBottom: 30,
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#FFFFFF',
    textAlign: 'center',
  },
  coursesContainer: {
    flex: 1,
    paddingHorizontal: 16,
  },
  scrollView: {
    marginBottom: 15,
  },
  courseItem: {
    backgroundColor: '#1A1A1A',
    paddingHorizontal: 16,
    paddingVertical: 8,
    borderRadius: 20,
    marginRight: 8,
  },
  courseText: {
    color: '#FFFFFF',
    fontSize: 16,
  },
  button: {
    backgroundColor: '#12B956',
    marginHorizontal: 16,
    marginBottom: 20,
    paddingVertical: 16,
    borderRadius: 12,
    alignItems: 'center',
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 16,
    fontWeight: 'bold',
  },
});

export default OnboardingScreen; 